package demo.controller;

import demo.domain.ClaimSystem;
import demo.domain.OrderStatus;
import demo.service.AuthorizationResponse;
import demo.service.ClaimSystemInfoService;
import demo.service.PaymentResponse;
import demo.service.PaymentService;
import demo.domain.OrderStatusUpdateMessage;
import demo.domain.Payment;
import demo.domain.PaymentStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ClaimsInfoController {
	
	private static final Logger log = LoggerFactory.getLogger(ClaimsInfoController.class);
	private static final String REQUEST_TIMEOUT_RESPONSE = "Sorry, server is busy. Please try again later.";

    private ClaimSystemInfoService claimSystemInfoService;
    
    private PaymentService paymentService;

    @Autowired
    public ClaimsInfoController(ClaimSystemInfoService claimSystemInfoService, PaymentService paymentService) {
        this.claimSystemInfoService = claimSystemInfoService;
        this.paymentService = paymentService;
    }

    // ------------ Get all HealthSystems ----------------------------------------------------------

    @RequestMapping(value = "/claimsystems", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Resource<ClaimSystem>> getAllClaims() {
    	System.out.println("In controller class***");
        return getClaimResourceList(this.claimSystemInfoService.findAllClaimSystem());
    }

    // ------------ Get HealthSystem by given name ----------------------------------------------------------

    @RequestMapping(value = "/claimsystem", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Resource<ClaimSystem>> getClaimSystemByName(@RequestParam(value = "name") String name) {
        return getClaimResourceList(this.claimSystemInfoService.getClaimSystemByNameContains(name));
    }

    private List<Resource<ClaimSystem>> getClaimResourceList(List<ClaimSystem> claimsystems) {
        List<Resource<ClaimSystem>> resources = new ArrayList<Resource<ClaimSystem>>(claimsystems.size());
        for(ClaimSystem healthsystem : claimsystems) {
            resources.add(getClaimResource(healthsystem));
        }
        return resources;
    }

    private Resource<ClaimSystem> getClaimResource(ClaimSystem claimsys) {
        Resource<ClaimSystem> resource = new Resource<>(claimsys);
        resource.add(linkTo(methodOn(ClaimsInfoController.class).getClaimSystemById(claimsys.getClaimsId())).withSelfRel());
        return resource;
    }

    // ------------ Get restaurant by id----------------------------------------------------------

    @RequestMapping(value = "/claimsystem/{claimSystemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Resource<ClaimSystem> getClaimSystemById(@PathVariable("healthSystemId") String claimSystemId) {
        return getClaimResource(this.claimSystemInfoService.getClaimSystemById(claimSystemId));
    }

    // ------------ Get all items in a restaurant given restaurantId ----------------------------------------------------------

    /*@RequestMapping(value = "/restaurant/{restaurantId}/items", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Item> getAllItems(@PathVariable("restaurantId") String restaurantId) {
        return this.healthSystemInfoService.findAllItems(restaurantId);
    }
*/
    // ------------ upload a health system ----------------------------------------------------------

    @RequestMapping(value = "/claimsystem", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createClaim(@RequestBody ClaimSystem claimsystem) {
        this.claimSystemInfoService.createClaims(claimsystem);
    }
    
    @RequestMapping(value = "/claim/{claimId}", method = RequestMethod.POST)
    public void updateClaimStatus(@PathVariable("claimId") String claimId,
                                  @RequestBody OrderStatusUpdateMessage orderStatusUpdateMessage) {
    	System.out.println("In controller class*** updateClaimStatus"+claimId);
        this.claimSystemInfoService.updateClaimStatus(claimId, orderStatusUpdateMessage);
    }

    // ------------ upload a list of restaurant ----------------------------------------------------------

    /*@RequestMapping(value = "/restaurants", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createRestaurants(@RequestBody List<HealthSystem> restaurants) {
        this.healthSystemInfoService.createRestaurants(restaurants);
    }*/
    
 // ------------ make a payment ----------------------------------------------------------

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<?>> makePayment(@RequestBody Payment payment) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        Payment newPayment = this.paymentService.savePayment(payment);
        ListenableFuture<AuthorizationResponse> authorizationResponse = this.paymentService.makePayment(newPayment);
        authorizationResponse.addCallback(new ListenableFutureCallback<AuthorizationResponse>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Fail to make a payment for Claim {}", newPayment.getClaimId(), throwable);
                newPayment.setPaymentStatus(PaymentStatus.TIMEOUT);
                ResponseEntity<String> responseEntity =
                        new ResponseEntity<String>(REQUEST_TIMEOUT_RESPONSE, HttpStatus.REQUEST_TIMEOUT);
                deferredResult.setResult(responseEntity);
                paymentService.savePayment(newPayment);
            }

            @Override
            public void onSuccess(AuthorizationResponse authorizationResponse) {
                PaymentResponse paymentResponse = new PaymentResponse();
                paymentResponse.setPaymentId(newPayment.getId());
                paymentResponse.setClaimId(newPayment.getClaimId());
                if(authorizationResponse.isApproved()) {
                    paymentResponse.setRandomDeliveryTime(5, 60);
                    paymentResponse.setApproved(true);
                    paymentResponse.setMessage("Claim has been completed successfully. Thank you.");
                    newPayment.setPaymentStatus(PaymentStatus.APPROVED);
                    OrderStatusUpdateMessage message = new OrderStatusUpdateMessage(newPayment.getId(), OrderStatus.COMPLETED);
                    log.info("updating status of Claim {}", newPayment.getClaimId());
                    //paymentService.updateOrderStatusAfterPayment(newPayment.getClaimId(), message);
                    claimSystemInfoService.updateClaimStatus(newPayment.getClaimId(), message);
                } else {
                    paymentResponse.setMessage("Payment is declined. Please re-check your payment method.");
                    newPayment.setPaymentStatus(PaymentStatus.DECLINED);
                }
                paymentService.savePayment(newPayment);
                ResponseEntity<PaymentResponse> responseEntity =
                        new ResponseEntity<PaymentResponse>(paymentResponse, HttpStatus.OK);
                deferredResult.setResult(responseEntity);
            }
        });
        return deferredResult;
    }
}

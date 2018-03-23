package demo.controller;

import demo.domain.ClaimSystem;
import demo.service.ClaimSystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ClaimsInfoController {

    private ClaimSystemInfoService claimSystemInfoService;

    @Autowired
    public ClaimsInfoController(ClaimSystemInfoService claimSystemInfoService) {
        this.claimSystemInfoService = claimSystemInfoService;
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

    // ------------ upload a list of restaurant ----------------------------------------------------------

    /*@RequestMapping(value = "/restaurants", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createRestaurants(@RequestBody List<HealthSystem> restaurants) {
        this.healthSystemInfoService.createRestaurants(restaurants);
    }*/
}

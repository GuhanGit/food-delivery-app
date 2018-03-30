package demo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Random;

@Data
public class PaymentResponse {
    private String paymentId;    
	private String claimId;
    private String message;
    private boolean isApproved;
    private boolean isTimeout;    
    private int deliveryTime;
    
    public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}


    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	
    public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
    public PaymentResponse() {
    }

    public PaymentResponse(String paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentResponse(@JsonProperty("paymentId") String paymentId,
                           @JsonProperty("claimId") String claimId,
                           @JsonProperty("isApproved") boolean isApproved,
                           @JsonProperty("isTimeout") boolean isTimeout,
                           @JsonProperty("message") String message) {
        this.paymentId = paymentId;
        this.claimId = claimId;
        this.isApproved = isApproved;
        this.isTimeout = isTimeout;
        this.message = message;
        if(this.isApproved) this.deliveryTime = getRandomDeliveryTime(5, 60);
    }

    // generate random number in the range of [5, 60]
    private int getRandomDeliveryTime(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public void setRandomDeliveryTime(int min, int max) {
        this.deliveryTime = getRandomDeliveryTime(min, max);
    }
}

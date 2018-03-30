package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Document(collection = "payments")
public class Payment {

    @Id
    private String id = UUID.randomUUID().toString();
    
	//@ManyToOne(targetEntity = CreditCard.class, cascade = CascadeType.ALL)
    private CreditCard creditCard;
    @Indexed
	private String claimId;
    @Indexed
	private double amount;  // in dollars
    
    private PaymentStatus paymentStatus;
  
	
	public String getClaimId() {
		return claimId;
	}

	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	

    public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Payment() {
    }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


    @JsonCreator
    public Payment(@JsonProperty("claimId") String claimId,
                   @JsonProperty("creditCard") CreditCard creditCard,
                   @JsonProperty("amount") double amount) {
        this.claimId = claimId;
        this.creditCard = creditCard;
        this.amount = amount;
        this.paymentStatus = PaymentStatus.PENDING;
    }

}

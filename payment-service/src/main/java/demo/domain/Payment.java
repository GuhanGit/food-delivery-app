package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Data
public class Payment {

    @Id
    private String id = UUID.randomUUID().toString();
    
	@ManyToOne(targetEntity = CreditCard.class, cascade = CascadeType.ALL)
    private CreditCard creditCard;
    
	private String orderId;
    public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	private double amount;  // in dollars
    private PaymentStatus paymentStatus;

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
    public Payment(@JsonProperty("orderId") String orderId,
                   @JsonProperty("creditCard") CreditCard creditCard,
                   @JsonProperty("amount") double amount) {
        this.orderId = orderId;
        this.creditCard = creditCard;
        this.amount = amount;
        this.paymentStatus = PaymentStatus.PENDING;
    }

}

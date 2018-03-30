package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Document(collection = "claims")
public class ClaimSystem {

    @Id
    private String claimsId;

    @Indexed
    private String providerNumber;
    
    @Indexed
    private String claimNumber;
    
    @Indexed
    private String claimStatusCode;
    
    @Indexed
    private String claimType;
    
    @Indexed
    private String claimTypeCode;
    
    @Indexed
    private String paymentStatus;
    

    //private List<Item> items = new ArrayList<>();

    // TODO
    // add location and deliveryInfo

   /* @GeoSpatialIndexed
    private Point location;
    private Address address;
    private String description;
    private DeliveryInfo deliveryInfo;
    private String phoneNumber;
    private String image; // image url*/

    // need to add hours (date, open time, closing time)
    // so that we can determine if the restaurant accepts delivery now.

    public ClaimSystem() {
    }

    @JsonCreator
    public ClaimSystem(@JsonProperty("claimsId") String claimsId,    		
    		@JsonProperty("providerNumber") String providerNumber,
    		@JsonProperty("claimNumber") String claimNumber,
    		@JsonProperty("claimStatusCode") String claimStatusCode,
    		@JsonProperty("claimType") String claimType,
    		@JsonProperty("claimTypeCode") String claimTypeCode,
    		@JsonProperty("paymentStatus") String paymentStatus
                     ) {
        this.claimsId = claimsId;
        this.providerNumber = providerNumber;
        this.claimNumber = claimNumber;
        this.claimStatusCode = claimStatusCode;
        this.claimType=claimType;
        this.claimTypeCode = claimTypeCode;
        this.paymentStatus=paymentStatus;
    }

	public String getClaimsId() {
		return claimsId;
	}

	public void setClaimsId(String claimsId) {
		this.claimsId = claimsId;
	}

	public String getProviderNumber() {
		return providerNumber;
	}

	public void setProviderNumber(String providerNumber) {
		this.providerNumber = providerNumber;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getClaimStatusCode() {
		return claimStatusCode;
	}

	public void setClaimStatusCode(String claimStatusCode) {
		this.claimStatusCode = claimStatusCode;
	}

	public String getClaimTypeCode() {
		return claimTypeCode;
	}

	public void setClaimTypeCode(String claimTypeCode) {
		this.claimTypeCode = claimTypeCode;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	

   

    
}

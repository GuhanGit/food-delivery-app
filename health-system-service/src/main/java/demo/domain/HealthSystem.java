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
@Document(collection = "healthsystem")
public class HealthSystem {

    @Id
    private String healthSystemId;

    @Indexed
    private String healthSystemName;
    
    @Indexed
    private String stateCode;
    
    @Indexed
    private String stateName;
    
    @Indexed
    private String type;
    

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

    public HealthSystem() {
    }

    @JsonCreator
    public HealthSystem(@JsonProperty("healthSystemId") String healthSystemId,    		
    		@JsonProperty("healthSystemName") String healthSystemName,
    		@JsonProperty("stateCode") String stateCode,
    		@JsonProperty("stateName") String stateName,
    		@JsonProperty("type") String type
                     ) {
        this.healthSystemId = healthSystemId;
        this.healthSystemName = healthSystemName;
        this.stateCode = stateCode;
        this.stateName = stateName;
        this.type = type;
       
    }

	public String getHealthSystemId() {
		return healthSystemId;
	}

	public void setHealthSystemId(String healthSystemId) {
		this.healthSystemId = healthSystemId;
	}

	public String getHealthSystemName() {
		return healthSystemName;
	}

	public void setHealthSystemName(String healthSystemName) {
		this.healthSystemName = healthSystemName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

   

    
}

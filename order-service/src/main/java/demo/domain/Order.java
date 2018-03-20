package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.*;

@Data
@Document(collection = "orders")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    @Id
    private String id;

    @Indexed
    private String userId;
    private String paymentId;
    public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	private OrderStatus orderStatus;
    public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	private List<Item> items = new ArrayList<>();
    private DeliveryInfo deliveryInfo;
    private double totalCost;
    private String note;
    @CreatedDate
    private Date creationTime;
    @LastModifiedDate
    private Date lastModifyTime;

    public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	private Map<OrderStatus, Date> updateHistory = new HashMap<>();

    public Map<OrderStatus, Date> getUpdateHistory() {
		return updateHistory;
	}

	public void setUpdateHistory(Map<OrderStatus, Date> updateHistory) {
		this.updateHistory = updateHistory;
	}

	public Order() {
    }

    @JsonCreator
    public Order(@JsonProperty("items") List<Item> items,
                 @JsonProperty("deliveryInfo") DeliveryInfo deliveryInfo,
                 @JsonProperty("note") String note) {
        this.id = id;
        this.items = items;
        this.deliveryInfo = deliveryInfo;
        this.note = note;
        this.creationTime = new Date();
        this.totalCost = calculateTotalCost(this.items);
        this.orderStatus = OrderStatus.PENDING;
        this.updateHistory.put(this.orderStatus, this.creationTime);
    }

    private double calculateTotalCost(List<Item> items) {
        double cost = 0L;
        for(Item item : items) {
            cost += item.getPrice()*100;
        }
        return cost/100.0;
    }

}

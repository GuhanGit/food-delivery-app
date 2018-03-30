package demo.domain.repository;


import demo.domain.ClaimSystem;
import demo.domain.OrderStatusUpdateMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClaimSystemRepositoryImpl implements ClaimSystemRepositoryCustom {

    private MongoTemplate mongoTemplate;

    @Autowired
    public ClaimSystemRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<ClaimSystem> findByNameContains(String claimName) {
    	claimName = ".*" + claimName + ".*";
        Query query = new Query();
        query.addCriteria(Criteria.where("claimType").regex(claimName, "i"));
        return mongoTemplate.find(query, ClaimSystem.class);
    }

	@Override
	public String updateStatus(String claimId, OrderStatusUpdateMessage orderStatusUpdateMessage) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(claimId));
		System.out.println(orderStatusUpdateMessage.getOrderStatus());
		Update update = new Update();
		update.set("paymentStatus", orderStatusUpdateMessage.getOrderStatus());
		//update.set("ic", 1111);
		ClaimSystem claim=mongoTemplate.findAndModify(query, update,new FindAndModifyOptions().returnNew(true), ClaimSystem.class);
		System.out.println("claim:::"+claim);
		return "Success";
	}
}

package demo.domain.repository;


import demo.domain.ClaimSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
}

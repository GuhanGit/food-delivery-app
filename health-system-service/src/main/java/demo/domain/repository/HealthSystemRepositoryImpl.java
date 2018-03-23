package demo.domain.repository;


import demo.domain.HealthSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HealthSystemRepositoryImpl implements HealthSystemRepositoryCustom {

    private MongoTemplate mongoTemplate;

    @Autowired
    public HealthSystemRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<HealthSystem> findByNameContains(String restaurantName) {
        restaurantName = ".*" + restaurantName + ".*";
        Query query = new Query();
        query.addCriteria(Criteria.where("healthSystemName").regex(restaurantName, "i"));
        return mongoTemplate.find(query, HealthSystem.class);
    }
}

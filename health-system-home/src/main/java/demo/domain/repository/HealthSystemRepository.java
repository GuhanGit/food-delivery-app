package demo.domain.repository;

import demo.domain.HealthSystem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HealthSystemRepository extends MongoRepository<HealthSystem, String>, HealthSystemRepositoryCustom {
}

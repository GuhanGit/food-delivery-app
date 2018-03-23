package demo.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import demo.domain.ClaimSystem;


@Repository
public interface ClaimSystemRepository extends MongoRepository<ClaimSystem, String>, ClaimSystemRepositoryCustom {
}

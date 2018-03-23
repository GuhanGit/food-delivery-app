package demo.domain.repository;

import demo.domain.HealthSystem;

import java.util.List;

public interface HealthSystemRepositoryCustom {

    List<HealthSystem> findByNameContains(String restaurantName);

}

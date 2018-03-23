package demo.service;

import demo.domain.HealthSystem;

import java.util.List;


public interface HealthSystemInfoService {

    List<HealthSystem> findAllHealthSystem();

    HealthSystem getHealthSystemById(String healthSystemById);

    List<HealthSystem> getHealthSystemByNameContains(String healthSystemName);

    //List<Item> findAllItems(String restaurantId);

    void createHealthSystem(HealthSystem healthsystem);

    //void createRestaurants(List<HealthSystem> restaurants);



    // TODO
    // get active items only
    // add sorting and pagination
    // find restaurants in one city
    // find restaurants near one location
}

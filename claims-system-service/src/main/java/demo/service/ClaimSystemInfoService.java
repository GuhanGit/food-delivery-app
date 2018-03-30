package demo.service;

import demo.domain.ClaimSystem;
import demo.domain.OrderStatusUpdateMessage;

import java.util.List;


public interface ClaimSystemInfoService {

    List<ClaimSystem> findAllClaimSystem();

    ClaimSystem getClaimSystemById(String claimSystemById);

    List<ClaimSystem> getClaimSystemByNameContains(String claimSystemName);

    //List<Item> findAllItems(String restaurantId);

    void createClaims(ClaimSystem claimsystem);
    
    String updateClaimStatus(String claimId, OrderStatusUpdateMessage orderStatusUpdateMessage);

    //void createRestaurants(List<HealthSystem> restaurants);



    // TODO
    // get active items only
    // add sorting and pagination
    // find restaurants in one city
    // find restaurants near one location
}

package demo.service.impl;

import demo.domain.ClaimSystem;
import demo.domain.OrderStatusUpdateMessage;
import demo.domain.repository.ClaimSystemRepository;
import demo.service.ClaimSystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimSystemServiceImpl implements ClaimSystemInfoService {

    private ClaimSystemRepository claimSystemRepository;

    @Autowired
    public ClaimSystemServiceImpl(ClaimSystemRepository claimSystemRepository) {
        this.claimSystemRepository = claimSystemRepository;
    }

    @Override
    public List<ClaimSystem> findAllClaimSystem() {
    	System.out.println("In service impl class*****");
    	List testList = this.claimSystemRepository.findAll();
    	System.out.println(testList.size());
        //return this.restaurantRepository.findAll();
    	return testList;
    }

    @Override
    public ClaimSystem getClaimSystemById(String claimSystemById) {
        return this.claimSystemRepository.findOne(claimSystemById);
    }

    @Override
    public List<ClaimSystem> getClaimSystemByNameContains(String claimSystemName) {
        return this.claimSystemRepository.findByNameContains(claimSystemName);
    }

   /* @Override
    public List<Item> findAllItems(String restaurantId) {
       // return this.healthSystemRepository.findOne(restaurantId).getItems();
    	return null;
    }*/

    @Override
    public void createClaims(ClaimSystem claimsystem) {
        this.claimSystemRepository.save(claimsystem);
    }

	@Override
	public String updateClaimStatus(String claimId, OrderStatusUpdateMessage orderStatusUpdateMessage) {
		return this.claimSystemRepository.updateStatus( claimId,  orderStatusUpdateMessage);
	}

   /* @Override
    public void createRestaurants(List<HealthSystem> restaurants) {
        this.healthSystemRepository.save(restaurants);
    }*/
}

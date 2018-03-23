package demo.service.impl;

import demo.domain.HealthSystem;
import demo.domain.repository.HealthSystemRepository;
import demo.service.HealthSystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthSystemServiceImpl implements HealthSystemInfoService {

    private HealthSystemRepository healthSystemRepository;

    @Autowired
    public HealthSystemServiceImpl(HealthSystemRepository healthSystemRepository) {
        this.healthSystemRepository = healthSystemRepository;
    }

    @Override
    public List<HealthSystem> findAllHealthSystem() {
    	System.out.println("In service impl class*****");
    	List testList = this.healthSystemRepository.findAll();
    	System.out.println(testList.size());
        //return this.restaurantRepository.findAll();
    	return testList;
    }

    @Override
    public HealthSystem getHealthSystemById(String healthSystemById) {
        return this.healthSystemRepository.findOne(healthSystemById);
    }

    @Override
    public List<HealthSystem> getHealthSystemByNameContains(String healthSystemName) {
        return this.healthSystemRepository.findByNameContains(healthSystemName);
    }

   /* @Override
    public List<Item> findAllItems(String restaurantId) {
       // return this.healthSystemRepository.findOne(restaurantId).getItems();
    	return null;
    }*/

    @Override
    public void createHealthSystem(HealthSystem healthsystem) {
        this.healthSystemRepository.save(healthsystem);
    }

   /* @Override
    public void createRestaurants(List<HealthSystem> restaurants) {
        this.healthSystemRepository.save(restaurants);
    }*/
}

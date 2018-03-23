package demo.controller;

import demo.domain.HealthSystem;
import demo.service.HealthSystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class HealthSystemInfoController {

    private HealthSystemInfoService healthSystemInfoService;

    @Autowired
    public HealthSystemInfoController(HealthSystemInfoService healthSystemInfoService) {
        this.healthSystemInfoService = healthSystemInfoService;
    }

    // ------------ Get all HealthSystems ----------------------------------------------------------

    @RequestMapping(value = "/healthsystems", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Resource<HealthSystem>> getAllHealthSystems() {
    	System.out.println("In controller class***");
        return getRestaurantResourceList(this.healthSystemInfoService.findAllHealthSystem());
    }

    // ------------ Get HealthSystem by given name ----------------------------------------------------------

    @RequestMapping(value = "/healthsystem", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Resource<HealthSystem>> getHealthSystemByName(@RequestParam(value = "name") String name) {
        return getRestaurantResourceList(this.healthSystemInfoService.getHealthSystemByNameContains(name));
    }

    private List<Resource<HealthSystem>> getRestaurantResourceList(List<HealthSystem> healthsystems) {
        List<Resource<HealthSystem>> resources = new ArrayList<Resource<HealthSystem>>(healthsystems.size());
        for(HealthSystem healthsystem : healthsystems) {
            resources.add(getRestaurantResource(healthsystem));
        }
        return resources;
    }

    private Resource<HealthSystem> getRestaurantResource(HealthSystem healthsys) {
        Resource<HealthSystem> resource = new Resource<>(healthsys);
        resource.add(linkTo(methodOn(HealthSystemInfoController.class).getHealthSystemById(healthsys.getHealthSystemId())).withSelfRel());
        return resource;
    }

    // ------------ Get restaurant by id----------------------------------------------------------

    @RequestMapping(value = "/healthsystem/{healthSystemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Resource<HealthSystem> getHealthSystemById(@PathVariable("healthSystemId") String healthSystemId) {
        return getRestaurantResource(this.healthSystemInfoService.getHealthSystemById(healthSystemId));
    }

    // ------------ Get all items in a restaurant given restaurantId ----------------------------------------------------------

    /*@RequestMapping(value = "/restaurant/{restaurantId}/items", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Item> getAllItems(@PathVariable("restaurantId") String restaurantId) {
        return this.healthSystemInfoService.findAllItems(restaurantId);
    }
*/
    // ------------ upload a health system ----------------------------------------------------------

    @RequestMapping(value = "/healthsystem", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createHealthSystem(@RequestBody HealthSystem healthsystem) {
        this.healthSystemInfoService.createHealthSystem(healthsystem);
    }

    // ------------ upload a list of restaurant ----------------------------------------------------------

    /*@RequestMapping(value = "/restaurants", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createRestaurants(@RequestBody List<HealthSystem> restaurants) {
        this.healthSystemInfoService.createRestaurants(restaurants);
    }*/
}

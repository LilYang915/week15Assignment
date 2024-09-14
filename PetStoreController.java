package pet.store.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
@Autowired
private PetStoreService petStoreService;

@PostMapping
@ResponseStatus(code = HttpStatus.CREATED)
public PetStoreData insertPetStoreData (
		@RequestBody PetStoreData petStoreData) {
	log.info("this is a log line",petStoreData);
	return petStoreService.savePetStore(petStoreData);
}
		
		
@PutMapping("/{petStoreId}")
public PetStoreData updatePetStoreData (
		@RequestBody PetStoreData petStoreData, @PathVariable Long petStoreId) {
	petStoreData.setPetStoreId(petStoreId);
	log.info("this is a log line",petStoreData);
	return petStoreService.savePetStore(petStoreData);
}
@PostMapping("/{petStoreId}/employee")
@ResponseStatus(code = HttpStatus.CREATED)
public PetStoreEmployee addEmployee(@RequestBody PetStoreEmployee employee, @PathVariable Long petStoreId) {
	log.info("Creating Employee{}", employee);
	return petStoreService.saveEmployee(petStoreId, employee );
	
}
@PostMapping("/{petStoreId}/customer")
@ResponseStatus(code = HttpStatus.CREATED)
public PetStoreCustomer addCustomer(@RequestBody PetStoreCustomer customer, @PathVariable Long petStoreId) {
	log.info("Creating Customer{}", customer);
	return petStoreService.addCustomer(petStoreId, customer);
}


@DeleteMapping("/{petStoreId}")
public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
	log.info("Deleting pet store with ID={}", petStoreId);
	petStoreService.deletePetStoreById(petStoreId);
	return Map.of("message", "Deletion of pet store with ID=" + petStoreId + " was sucessful" );

}
//@DeleteMapping("/{customerId}")
/*public Map<String, String> deleteCustomerById(@PathVariable Long customerId) {
	log.info("Deleting customer with ID={}", customerId);
	petStoreService.deleteCustomerById(customerId);
	return Map.of("message", "Deletion of customer with ID=" + customerId + " was sucessful" );
}*/
}


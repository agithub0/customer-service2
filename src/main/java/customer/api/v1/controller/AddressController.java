package customer.api.v1.controller;
import customer.api.v1.model.Customer;
import customer.api.v1.model.CustomerAddress;
import customer.api.v1.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

    @Autowired
    AddressService addressService;

    //TO GET ALL ADDRESSES
    @GetMapping("/addresses")
    public List<CustomerAddress> getAllAddress(){
        return addressService.getAllAddress();
    }

    //TO GET ADDRESS BY ID
    @GetMapping("addresses/{id}")
    public CustomerAddress getAddressById(@PathVariable int id){
        return addressService.getAddressById(id);
    }

    //TO GET CUSTOMER NAME BY ADDRESS ID
    @GetMapping("addresses/{id}/customer")
    public Customer getCustomerWithAddressId(@PathVariable("id") int id){
        return addressService.getCustomerWithAddressId(id);
    }

    //CREATE ADDRESS(CUSTOMER ID IN BODY)
    @PostMapping("/address")
    public CustomerAddress createAddress(@Valid @RequestBody CustomerAddress customerAddress1){
        return addressService.createCustomerAddress(customerAddress1);
    }

    //UPDATE ADDRESS FOR SPECIFIC ADDRESS ID
    @PutMapping("addresses/{id}")
    public CustomerAddress updateCustomerById(@PathVariable int id,@Valid @RequestBody CustomerAddress customerAddress1){
        return addressService.updateAddressById(id,customerAddress1);
    }

    //DELETE ADDRESS FOR SPECIFIC ADDRESS ID
    @DeleteMapping("addresses/{id}")
    public void deleteAddressById(@PathVariable("id") int id){
        addressService.deleteAddressById(id);
    }

    //DELETE ADDRESS FOR SPECIFIC CUSTOMER ID
    @DeleteMapping("addresses/customer/{id}")
    public void deleteAddressByCustomerId(@PathVariable("id") int id){
        addressService.deleteAddressByCustomerId(id);
    }

}

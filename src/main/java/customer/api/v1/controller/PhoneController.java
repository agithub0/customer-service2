package customer.api.v1.controller;

import customer.api.v1.model.Phone;
import customer.api.v1.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PhoneController {


    @Autowired
    PhoneService phoneService;

    //TO GET ALL PHONE NUMBERS
    @GetMapping("/phoneNumbers")
    public List<Phone> getAllPhones(){
        return phoneService.getAllPhones();
    }

    //TO GET PHONE NUBMER OF SPECIFIC ID
    @GetMapping("/phoneNumbers/{id}")
    public Phone getPhoneById(@PathVariable("id") int id){
        return phoneService.getPhoneById(id);
    }

    //TO CREATE PHONE NUMBER OF A CUSTOMER
    @PostMapping("/phoneNumber")
    public Phone createPhone(@Valid @RequestBody Phone phone1){
        return phoneService.createPhone(phone1);
    }

    //TO UPDATE A PHONE NUMBER BY SPECIFIC ID
    @PutMapping("/phoneNumbers/{id}")
    public Phone updatePhoneById(@PathVariable("id") int id,@Valid @RequestBody Phone phone1){
        return phoneService.updatePhoneById(id,phone1);
    }

    //TO DELETE PHONE NUMBER BY SPECIFIC ID
    @DeleteMapping("/phoneNumbers/{id}")
    public void deletePhoneById(@PathVariable("id") int id){
        phoneService.deletePhoneById(id);
    }

    //TO DELETE PHONE NUMBER OF A CUSTOMER
    @DeleteMapping("/phoneNumbers/customer/{id}")
    public void deletePhoneByCustomerId(@PathVariable("id") int id){
        phoneService.deletePhoneByCustomerId(id);
    }


}

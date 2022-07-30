package customer.api.v1.service;

import customer.api.v1.exception.PhoneNotFoundException;
import customer.api.v1.model.Customer;
import customer.api.v1.model.Phone;
import customer.api.v1.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService{

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    CustomerService customerService;

    RestTemplate restTemplate = new RestTemplate();

    //GET ALL PHONE NUMBERS
    @Override
    public List<Phone> getAllPhones() {
        List<Phone> phones = phoneRepository.findAll();
        if(phones == null || phones.isEmpty()){
            throw new PhoneNotFoundException("No phone numbers are available...");
        }else{
            return phones;
        }
    }

    //GET PHONE NUMBERS BY PHONEID
    @Override
    public Phone getPhoneById(int id) {
        if(phoneRepository.existsById(id)){
            return phoneRepository.findById(id).get();
        }else{
            throw new PhoneNotFoundException("No Phone number is available with given id");
        }
    }

    //CREATE PHONE NUMBER FOR SPECIFIC CUSTOMER ID
    @Override
    public Phone createPhone(Phone phone1) {
        Integer temp = phone1.getCustomerId();
        if(temp == null || temp == 0){
            throw new PhoneNotFoundException("Customer Id cannot be null");
        }else{
            try {
                Customer customer = customerService.getCustomerById(temp);
                if(customer==null){
                    
                }
                return phoneRepository.save(phone1);
            }catch(Exception e) {
                throw new PhoneNotFoundException("Invalid customer id...");
            }
        }
    }

    //UPDATE PHONE BY PHONE ID
    @Override
    public Phone updatePhoneById(int id, Phone phone1) {
        if(phoneRepository.existsById(id)){
            Phone existingPhone = phoneRepository.findById(id).get();
            existingPhone.setAreaCode(phone1.getAreaCode());
            existingPhone.setLocalNumber(phone1.getLocalNumber());
            return phoneRepository.save(existingPhone);
        }else{
            throw new PhoneNotFoundException("Phone number for given id does not exist...");
        }
    }

    //DELETE BY PHONE ID
    @Override
    public void deletePhoneById(int id) {
        if(phoneRepository.existsById(id)){
            phoneRepository.deleteById(id);
        }else{
            throw new PhoneNotFoundException("phone number for given id does not exist...");
        }
    }

    //DELETE BY CUSTOMER ID
    public void deletePhoneByCustomerId(int id){
        List<Phone> phoneList = getAllPhones();
        List<Phone> list = new ArrayList<>();
        for(int index=0;index<phoneList.size();index++){
            Phone temp = phoneList.get(index);
            if(temp.getCustomerId() == id){
                list.add(temp);
            }
        }
        for(int index=0;index<list.size();index++){
            deletePhoneById(list.get(index).getPhoneId());
        }

    }


}

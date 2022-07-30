package customer.api.v1.service;

import java.util.ArrayList;
import java.util.List;

import customer.api.v1.model.Customer;
import customer.api.v1.model.CustomerAddress;
import customer.api.v1.model.Phone;
import customer.api.v1.exception.CustomerNotFoundException;
import customer.api.v1.exception.InvalidCustomerInputException;
import customer.api.v1.repository.AddressRepository;
import customer.api.v1.repository.CustomerRepository;
import customer.api.v1.repository.PhoneRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    
//REPOSITORIES
	@Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PhoneRepository phoneRepository;


    ModelMapper modelmapper;
    //HARD CODED INITIALIZED BRCAUSE OF CYCLIC TRAP
    AddressService addressService = new AddressServiceImpl();
    PhoneService phoneService = new PhoneServiceImpl();

    //CONSTRUCTOR
    public CustomerServiceImpl() {
    	modelmapper=new ModelMapper();
    	modelmapper.getConfiguration().setSkipNullEnabled(true);
        modelmapper.addMappings(new PropertyMap<Customer, Customer>() {
            @Override
            protected void configure() {
                skip(destination.getCustomerId());
            }
        });
    	}

    //TO GET ALL CUSTOMERS // NO PARAMETER REQUIRED
    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> output = customerRepository.findAll();
        if (output == null || output.size() == 0) {
            throw new CustomerNotFoundException("No customers available...");
        } else {
            return output;
        }
    }

    //TO GET CUSTOMER BY USERNAME // USERNAME WILL BE PROVIDED AS A QUERY PARAMETER...
    @Override
    public Customer getCustomerByUserName(String username) {
        List<Customer> list = customerRepository.findAll();
        for (int index = 0; index < list.size(); index++) {
            if (list.get(index).getUserName().equals(username)) {
                return list.get(index);
            }
        }
        throw new CustomerNotFoundException("Customer with given username does not exist...");
    }

    //TO GET CUSTOMER BY FIRST AND LAST NAME // FIRST AND LAST NAME WILL BE PROVIDED AS A QUERY PARAMETER
    @Override
    public List<Customer> getCustomerByFirstNameAndLastName(String firstname, String lastname) {
        List<Customer> output = new ArrayList<>();
        List<Customer> list = customerRepository.findAll();
        for (int index = 0; index < list.size(); index++) {
            if (list.get(index).getFirstName().equals(firstname) && list.get(index).getLastName().equals(lastname)) {
                output.add(list.get(index));
            }
        }
        if (output == null || output.isEmpty()) {
            throw new CustomerNotFoundException("Customer with given first name and last name does not exist...");
        } else {
            return output;
        }

    }

    //TO GET CUSTOMERS BY ID // ID WILL BE PROVIDED IN THE PATH VARIABLE
    @Override
    public Customer getCustomerById(int id) {
        if (customerRepository.existsById(id)) {
            return customerRepository.findById(id).get();
        } else {
            throw new CustomerNotFoundException("Customer with given id does not exists...");
        }
    }

    //TO CREATE CUSTOMERS // CUSTOMER WILL BE PROVIDED AS A REQUEST BODY AND CUSTOMER'S ID IS AUTO-GENERATED
    @Override
    public Customer createCustomer(Customer customer) {
        if (customer == null) {
            throw new InvalidCustomerInputException("Invalid input...");
        }
        try {
            return customerRepository.save(customer);
        }
        catch (Exception e){
            Throwable exception=e.getCause();
            throw new InvalidCustomerInputException( exception instanceof ConstraintViolationException ? "User with given username already exists" : exception.getMessage());
        }
    }


    //TO UPDATE CUSTOMERS // CUSTOMER ID IS PROVIDES IN THE PATH VARIABLE AND CUSTOMER OBJECT IN THE REQUEST BODY.
    // NO NEED TO PROVIDE CUSTOMER ID AGAIN IN THE CUSTOMER'S REQUEST BODY OBJECT...
    @Override
    public Customer updateCustomerById(int id, Customer customer1) {
    	if(customer1.getCustomerId()!=0 && customer1.getCustomerId()!=id )
    		throw new CustomerNotFoundException("Customer Id Doesnt match");
    	else if (customerRepository.existsById(id)) {
            Customer existingCustomer = customerRepository.findById(id).get();
            modelmapper.map(customer1, existingCustomer);

            System.out.print(existingCustomer.toString());
            return customerRepository.save(existingCustomer);
        } else {
            throw new CustomerNotFoundException("Customer with given id does not exist...");
        }
    }

    @Override
    public void deleteCustomerById(int id) {
        if (customerRepository.existsById(id)) {

                List<CustomerAddress> addressList = addressRepository.findAll();
                List<CustomerAddress> temp = new ArrayList<>();
                if(addressList != null) {
                    for (int index = 0; index < addressList.size(); index++) {
                        if(addressList.get(index).getCustomerId() == id){
                            temp.add(addressList.get(index));
                        }
                    }
                }
                for(int i=0;i<temp.size();i++){
                    addressRepository.deleteById(temp.get(i).getAddressId());
                }


                List<Phone> phoneList = phoneRepository.findAll();
                List<Phone> temp1 = new ArrayList<>();
                if(phoneList != null){
                    for(int index=0;index<phoneList.size();index++){
                        if(phoneList.get(index).getCustomerId() == id){
                           temp1.add(phoneList.get(index));
                        }
                    }

                for(int index=0;index<temp.size();index++){
                    phoneRepository.deleteById(temp1.get(index).getPhoneId());
                }
            }
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException("Customer with given Id does not exist...");
        }
    }
}

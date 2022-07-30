package customer.api.v1.service;

import customer.api.v1.model.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> getAllCustomers();
    public Customer getCustomerById(int id);
    public Customer createCustomer(Customer customer);
    public Customer updateCustomerById(int id,Customer customer);
    public void deleteCustomerById(int id);
    public Customer getCustomerByUserName(String username);
    public List<Customer> getCustomerByFirstNameAndLastName(String firstname, String lastname);


}

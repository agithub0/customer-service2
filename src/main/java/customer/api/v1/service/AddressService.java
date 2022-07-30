package customer.api.v1.service;

import customer.api.v1.model.Customer;
import customer.api.v1.model.CustomerAddress;

import java.util.List;

public interface AddressService {

    public void deleteAddressById(int id);
    public Customer getCustomerWithAddressId(int id);
    public List<CustomerAddress> getAllAddress();
    public CustomerAddress getAddressById(int id);
    public CustomerAddress updateAddressById(int id,CustomerAddress customerAddress1);
    public CustomerAddress createCustomerAddress(CustomerAddress customerAddress);
    public void deleteAddressByCustomerId(int id);

}

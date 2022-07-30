package customer.api.v1.service;

import customer.api.v1.model.Phone;

import java.util.List;

public interface PhoneService {

    public List<Phone> getAllPhones();
    public Phone getPhoneById(int id);
    public Phone createPhone(Phone phone1);
    public Phone updatePhoneById(int id,Phone phone1);
    public void deletePhoneById(int id);
    public void deletePhoneByCustomerId(int id);


}

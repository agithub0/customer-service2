package customer.api.v1.service;

import customer.api.v1.exception.ZipCodeException;
import customer.api.v1.model.CustomerAddress;
import customer.api.v1.model.ZipCode;
import customer.api.v1.repository.AddressRepository;
import customer.api.v1.repository.ZipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.zip.ZipException;
@Service
public class ZipCodeImpl implements ZipService{

    @Autowired
    ZipRepository zipRepository;
    //To avoid cyclic dependency object is created using new keyword...
    @Autowired
    AddressRepository addressRepository;
    @Override
    public List<ZipCode> getAll() {
        List<ZipCode> temp =  zipRepository.findAll();
        if(temp == null || temp.size() == 0){
            throw new ZipCodeException("No zip codes available...");
        }else{
            return temp;
        }
    }

    @Override
    public ZipCode getById(int id) {
        if(zipRepository.existsById(id))
            return zipRepository.findById(id).get();
        else
            throw new ZipCodeException("Zip code for given id not found...");
    }

    @Override
    public ZipCode updateById(int id, ZipCode zipCode) {
        if(zipRepository.existsById(id)){
            ZipCode existingZip = zipRepository.findById(id).get();
            existingZip.setZipCode(zipCode.getZipCode());
            existingZip.setZipCodeExt(zipCode.getZipCodeExt());
            existingZip.setStateCode(zipCode.getStateCode());
            existingZip.setCity(zipCode.getCity());
            return zipRepository.save(existingZip);
        }else{
            throw new ZipCodeException("Zip code for given id not found...");
        }
    }

    @Override
    public ZipCode createZipCode(ZipCode zipCode) {
        return zipRepository.save(zipCode);
    }

    @Override
    public void deleteZipCode(int id) {
        if(zipRepository.existsById(id)){
            List<CustomerAddress> temp = addressRepository.findAll();
            if(temp != null) {
                for (int index = 0; index < temp.size(); index++) {
                    if (temp.get(index).getZipCodeId() == id) {
                        addressRepository.deleteById(temp.get(index).getAddressId());
                    }
                }
            }
            zipRepository.deleteById(id);
        }else{
            throw new ZipCodeException("invalid zip code id...");
        }
    }
}

package customer.api.v1.service;

import customer.api.v1.model.ZipCode;

import java.util.List;

public interface ZipService {

    public List<ZipCode> getAll();
    public ZipCode getById(int id);
    public ZipCode updateById(int id,ZipCode zipCode);
    public ZipCode createZipCode(ZipCode zipCode);
    public void deleteZipCode(int id);


}

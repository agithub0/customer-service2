package customer.api.v1.service;

import customer.api.v1.model.Users;
import java.util.List;

public interface UserService {

    public Users createUser(Users user);
    public Users updateUser(String username,Users user);
    public void deleteUser(String username);
    List<Users> getALlUsers();
    Users getById(String username);
}


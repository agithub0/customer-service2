package customer.api.v1.controller;

import customer.api.v1.model.Users;
import customer.api.v1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return userService.getALlUsers();
    }

    @GetMapping("/users/id")
    public Users getById(@PathVariable("id") String username){
        return userService.getById(username);
    }

    @PostMapping("/user")
    public Users createUser(@Valid @RequestBody Users user){
        return userService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public Users updateUser(@PathVariable("id") String username,@Valid @RequestBody Users user){
        return userService.updateUser(username,user);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") String username){
        userService.deleteUser(username);
    }
}
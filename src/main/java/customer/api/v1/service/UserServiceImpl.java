package customer.api.v1.service;

import customer.api.v1.exception.UserException;
import customer.api.v1.model.Users;
import customer.api.v1.repository.UserRepository;
import customer.api.v1.security.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    public String encoder(String rawPassword){
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    //TO CREATE USER
    @Override
    public Users createUser(Users user) {
        //GETTING PRINCIPAL...
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // ANONYMOUS USER CAN CREATE USER WITH ONLY USER ROLE
        if ((authentication instanceof AnonymousAuthenticationToken)) {

            if(user.getRole()!=null)
            {
                throw new UserException("Anonymous User cannot assign Roles...");
            }
            else if(userRepository.numberOfEntities()==0){
                user.setRole(Roles.ROLE_ADMIN.name());
            }
            else
            {user.setRole(Roles.ROLE_USER.name());}
        }

        //USER WITH ADMIN ROLE CAN CREATE USER WITH ANY ROLE
        else if(authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
            if(user.getRole()==null)
            {
                throw new UserException("Roles are not Assigned");
            }
            else {
                user.setRole(user.getRole().equalsIgnoreCase("ADMIN") ? Roles.ROLE_ADMIN.name() : Roles.ROLE_USER.name());
            }
        }

        //IF LOGGED IN AS USER WITH USER ROLE YOU ARE NOT ALLOWED TO CREATE NEW USER
        else if(authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_USER"))) {
            throw new UserException("You are already logged in as "+authentication.getName());
        }

        //SETTING ENABLE AND BYCRYPTING PASSWORD
        user.setEnabled(true);
        user.setPassword(encoder(user.getPassword()));

        //SAVING IN DB
        try{
            return userRepository.save(user);
        }
        /*catch (ConstraintViolationException e){
            throw new UserException("User with given username already exists");
        }*/
        catch (Exception e){
            Throwable exception=e.getCause();
            throw new UserException( exception instanceof ConstraintViolationException? "User with given username already exists" : exception.getMessage());
        }
    }


    @Override
    public Users updateUser(String username, Users user) {
        if(userRepository.existsById(username)){
            Users existingUser = userRepository.findById(username).get();
            existingUser.setEnabled(true);
            existingUser.setUsername(username);
            existingUser.setPassword(encoder(user.getPassword()));
            existingUser.setRole(user.getRole());
            return userRepository.save(existingUser);
        }else{
            throw new UserException("User with given username does not exist...");
        }
    }

    @Override
    public void deleteUser(String username) {
        if(userRepository.existsById(username)){
            userRepository.deleteById(username);
        }else{
            throw new UserException("User with given username does not exist...");
        }
    }

    @Override
    public List<Users> getALlUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getById(String username) {
        return userRepository.findById(username).get();
    }


}

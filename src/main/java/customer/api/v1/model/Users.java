package customer.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="userGen")
    @SequenceGenerator(name="userGen",sequenceName="userSeq",allocationSize = 1)
    private int userId;
    @NotEmpty(message = "Username cannot be empty")
    @Column(unique = true)
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    private String role;
    private boolean enabled;

}


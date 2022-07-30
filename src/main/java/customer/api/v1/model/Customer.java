package customer.api.v1.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import customer.api.v1.validator.genderValidation;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="customerGen")
    @SequenceGenerator(name="customerGen",sequenceName="customerSeq",allocationSize = 1)
    @JsonView(View.CustomerView.class)
    private int customerId;

    @NotEmpty(message = "First Name should not be empty!!")
    @JsonView(View.CustomerView.class)
    private String firstName;

    @JsonView(View.CustomerView.class)
    @NotEmpty(message = "Last Name should not be empty!!")
    private String lastName;

    @JsonView(View.CustomerView.class)
    @NotEmpty(message = "User Name should not be empty!!")
    @Column(unique = true)
    private String userName;

    @JsonView(View.CustomerView.class)
    @NotNull(message = "Age should not be empty !!")
    private int age;

    @JsonView(View.CustomerView.class)
    @NotEmpty(message = "Gender should not be empty!!")
    @genderValidation
    private String gender;

    @JsonView(View.CustomerView.class)
    private String organizationName;

    @JsonView(View.CustomerView.class)
    private String industry;

    @OneToMany(targetEntity = CustomerAddress.class, orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customerId",referencedColumnName = "customerId")
    @JsonView(View.AddressView.class)
    List<CustomerAddress> addressList;

    @JsonView(View.PhoneView.class)
    @OneToMany(targetEntity = Phone.class, orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    List<Phone> phoneList;
    
    //TO GET ALL VALUES FOR HTML TABLE
    public String getvalues(String key) {
    	switch(key)
    	{
    	case "FirstName": return firstName;
    	case "LastName": return lastName;
    	case "Username": return userName;
    	case "Age":	return Integer.toString(age);
    	case "Gender": return gender;
    	case "Organization": return organizationName;
    	case "Industry":	return industry;
    	case "Address": return this.addressList.toString();	
    	case "Phone Number": return this.phoneList.toString();
    	}
    	return null;
    }

}

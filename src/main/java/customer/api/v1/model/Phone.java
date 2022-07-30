package customer.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import customer.api.v1.validator.ContactNumberConstraint;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer_phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="phoneGen")
    @SequenceGenerator(name="phoneGen",sequenceName="phoneSeq",allocationSize = 1)
    private Integer phoneId;
    @NotNull(message = "Area code cannot be null !!")
    private Integer areaCode;
    @NotEmpty(message = "Number cannot be empty !!")
    @ContactNumberConstraint
    private String localNumber;
    @NotNull(message = "Customer id cannot be null !!")
    private int customerId;
    
    @Override
	public String toString() {
		return "areaCode=" + areaCode + "\n localNumber=" + localNumber;
	}
}

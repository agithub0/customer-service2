package customer.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_address")
public class CustomerAddress {

    //Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="addressGen")
    @SequenceGenerator(name="addressGen",sequenceName="addressSeq",allocationSize = 1)
    private int addressId;

    //foreign key
    @NotNull(message = "Customer Id cannot be null")
    private int customerId;

    @NotEmpty(message = "Address cannot be null")
    private String addressLine1;

    @NotEmpty(message = "Address cannot be null")
    private String addressLine2;

    @NotEmpty(message = "Address cannot be null")
    private String addressLine3;

    //foreign key...
    @NotNull(message = "zip code id cannot be null")
    int zipCodeId;

    @Override
	public String toString() {
		return "addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", addressLine3=" + addressLine3;
	}
}

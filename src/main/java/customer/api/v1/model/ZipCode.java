package customer.api.v1.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "zipcode")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ZipCode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ZipGen")
    @SequenceGenerator(name ="ZipGen",sequenceName = "ZipSeq",allocationSize = 1)
    @JsonView(View.zipView.class)
    int zipCodeId;
    @NotNull(message = "city cannot be null!")
    @JsonView(View.zipView.class)
    String city;
    @NotNull(message = "state code cannot be null!")
    @JsonView(View.zipView.class)
    int stateCode;
    @NotNull(message = "zip code cannot be null!")
    @JsonView(View.zipView.class)
    int zipCode;
    @JsonView(View.zipView.class)
    int zipCodeExt;

    @OneToMany(targetEntity = CustomerAddress.class, orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "zipCodeId",referencedColumnName = "zipCodeId")
    List<CustomerAddress> addressList;

}

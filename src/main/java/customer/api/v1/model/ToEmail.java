package customer.api.v1.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
public class ToEmail {
	private String to;
	private String subject;

	public ToEmail(String to,String subject){
		this.to=to;
		this.subject=subject;
	}
	
}

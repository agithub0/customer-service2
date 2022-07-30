package customer.api.v1.controller;

import javax.mail.MessagingException;

import customer.api.v1.exception.MailNotDelieveredException;
import customer.api.v1.service.EmailServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MailServiceController {
	
	  	@Autowired
	    private EmailServiceImplement emailService;

		  //TO GET ALL DETAILS OF CUSTOMER IN PROVIDED MAIL
	    @GetMapping("/customers/mail")
	    public ResponseEntity<String> Customers(@RequestParam String sendto,@RequestParam String subject){
	    	try {
				emailService.sendSimpleMessage(sendto,subject);
			} catch (MessagingException e) {
				throw new MailNotDelieveredException();
			}

			//
	        return ResponseEntity.ok("Success! Mail Sent");
	    }

}

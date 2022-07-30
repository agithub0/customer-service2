package customer.api.v1.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import customer.api.v1.model.FromEmail;


@Service
public class EmailServiceImplement implements EmailService {
	 @Autowired
	    private JavaMailSenderImpl emailSender;
	 @Autowired
	 	private FromEmail from;
	 @Autowired
	 CustomerService customerService;
	 @Autowired
	 private TemplateEngine templateEngine;
	 @Value("${customer.values}")
		List<String>header;
	 RestTemplate resttemplate=new RestTemplate();
	    @Override
	    public void sendSimpleMessage(String to, String subject) throws MessagingException {

	    	//HTML MESSAGE
	    	String text=message();
	    	
	    	//CREATE MAIL SENDER
	    	emailSender.setHost(from.getHost());
	    	emailSender.setUsername(from.getUsername());
	    	emailSender.setPassword(from.getPassword());
	    	emailSender.setPort(from.getPort());
	    	
			//CREATE EMAIL INSATNCE
	    	MimeMessage message = emailSender.createMimeMessage();
	    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    	helper.setTo(to);
	    	helper.setText(text, true);
			emailSender.send(message);
			 
	    }
	    
	    //TO CREATE HTML MESSAGE
	    public String message() {
	    	
	    	Context context = new Context();
	    	context.setVariable("customers",customerService.getAllCustomers());
	    	context.setVariable("headers", header);
	    	String text = templateEngine.process("email-template", context);
	    	return text;
	    }

}

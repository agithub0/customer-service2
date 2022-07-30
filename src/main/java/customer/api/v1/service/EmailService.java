package customer.api.v1.service;

import javax.mail.MessagingException;

public interface EmailService {
	 void sendSimpleMessage(String to, String subjects) throws MessagingException;
}

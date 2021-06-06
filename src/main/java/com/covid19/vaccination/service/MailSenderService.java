/**
 * 
 */
package com.covid19.vaccination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author ab68478
 *
 */
@Service
public class MailSenderService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendmail(String message,String subject,String subscriberMailAddress) {
		
		String from = "vaccinationalerts@gmail.com";
		 
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		 
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(subscriberMailAddress);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		 
		javaMailSender.send(simpleMailMessage);
	}

}

/**
 * 
 */
package com.covid19.vaccination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${slots.mail.notifier}")
	private boolean slotsMailNotifier;

	public void sendmail(String message, String subject, String subscriberMailAddress) {
		if (slotsMailNotifier) {
			String from = "vaccinationalerts@gmail.com";

			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

			simpleMailMessage.setFrom(from);
			simpleMailMessage.setTo(subscriberMailAddress);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(message);

			javaMailSender.send(simpleMailMessage);
		}
	}

}

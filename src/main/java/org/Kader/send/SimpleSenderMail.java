package org.Kader.send;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.Kader.config.EmailSettings;
import org.Kader.entities.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class SimpleSenderMail {

	private EmailSettings emailSettings;

	private JavaMailSender mailSender;
    
	

	public SimpleSenderMail(@Qualifier("JavaMailSenderBean")JavaMailSender mailSender,EmailSettings emailSettings) {
		this.mailSender=mailSender;
		this.emailSettings = emailSettings;
	}
	

	public void SendEmail(EmailDto emailDto) {
		MimeMessagePreparator preparator = mimeMessage-> {
			InternetAddress senderAddress=new InternetAddress(emailSettings.getSenderEmail(),emailSettings.getSenderName()); 
			InternetAddress receiverAddress=new InternetAddress(emailDto.getReceiverEmail(),emailDto.getReceiverName());
			mimeMessage.setSender(senderAddress);
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
			helper.setFrom(senderAddress);
			helper.setTo(receiverAddress);
			helper.setSubject(emailDto.getSubject());
			helper.setText(emailDto.getMessage());	
		};
		mailSender.send(preparator);
	}
}

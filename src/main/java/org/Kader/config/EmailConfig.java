package org.Kader.config;

import java.util.Properties;

import org.Kader.contants.EmailConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class EmailConfig {
	
	@Autowired 
	private EmailSettings emailSettings;
	
	
	
	@Bean("JavaMailSenderBean")
	public JavaMailSenderImpl JavaMailSender() {
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		mailSender.setHost(emailSettings.getHost());
		mailSender.setPort(emailSettings.getPort());
		mailSender.setUsername(emailSettings.getUsername());
		mailSender.setPassword(emailSettings.getPassword());
		mailSender.setProtocol(emailSettings.getProtocol());
		mailSender.setJavaMailProperties(getMailProperties());
		return mailSender;
	}
	 
	private Properties getMailProperties() {
		Properties props =new Properties();
		props.put(EmailConstants.SMTP_AUTH,emailSettings.getAuth());
		props.put(EmailConstants.SMTP_STARTLS,emailSettings.getStarttlsEnable());
		return props;	
	}
}

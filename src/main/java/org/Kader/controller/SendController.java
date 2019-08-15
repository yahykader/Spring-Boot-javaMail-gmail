package org.Kader.controller;

import org.Kader.entities.EmailDto;
import org.Kader.send.SimpleSenderMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {
	
	@Autowired
	private SimpleSenderMail simpleSenderMail;

	@GetMapping("/send")
	public void sendEmail() {
		simpleSenderMail.SendEmail(EmailDto.builder()
											.receiverName("test")
											.receiverEmail("yahyaouiabdelkader85@gmail.com")
											.subject("test test")
											.message("have a nice day")
											.build());
	}

}

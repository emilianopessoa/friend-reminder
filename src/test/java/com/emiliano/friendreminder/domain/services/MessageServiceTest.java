package com.emiliano.friendreminder.domain.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.emiliano.friendreminder.domain.valueobjects.Email;
import com.emiliano.friendreminder.domain.valueobjects.Mobile;

/**
 * This class provides test cases for {@link MessageService}.
 * 
 * @author Emiliano Pessoa
 */
class MessageServiceTest {

	/**
	 * Test sending an SMS message.
	 */
	@Test
	void sendSMSTest() {
		MessageService messageService = Mockito.mock(MessageService.class);
		Mobile mobile = new Mobile("1234567890");
		String subject = "Test";
		String body = "This is a test message.";

		messageService.sendSMS(mobile, subject, body);

		Mockito.verify(messageService).sendSMS(mobile, subject, body);
	}

	/**
	 * Test sending an email message.
	 */
	@Test
	void sendEmailTest() {
		MessageService messageService = Mockito.mock(MessageService.class);
		Email email = new Email("test@test.com");
		String subject = "Test";
		String body = "This is a test message.";

		messageService.sendEmail(email, subject, body);

		Mockito.verify(messageService).sendEmail(email, subject, body);
	}
}

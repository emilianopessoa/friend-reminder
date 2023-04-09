/**
 * 
 */
package com.emiliano.friendreminder.infrastructure.services.impl;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.emiliano.friendreminder.domain.services.MessageService;
import com.emiliano.friendreminder.domain.valueobjects.Email;
import com.emiliano.friendreminder.domain.valueobjects.Mobile;

/**
 * 
 * A service implementation that sends messages via SMS or email, using the
 * provided {@link Mobile} and {@link Email} objects.
 * 
 * As a test application, emails and SMS will only be displayed in the LOG.
 * 
 * @author Emiliano Pessoa
 *
 */
@Service
public class MessageServiceImpl implements MessageService {

	private static final Logger logger = Logger.getLogger(MessageServiceImpl.class.getName());

	@Override
	public void sendSMS(Mobile mobile, String subject, String body) {
		logger.info("\nSMS TO: " + mobile.getValue() + "\nSUBJECT:" + subject + "\nBODY:" + body + "\n");
	}

	@Override
	public void sendEmail(Email email, String subject, String body) {
		logger.info("\nEMAIL TO: " + email.getValue() + "\nSUBJECT:" + subject + "\nBODY:" + body + "\n");
	}

}

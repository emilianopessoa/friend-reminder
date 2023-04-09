package com.emiliano.friendreminder.domain.services;

import com.emiliano.friendreminder.domain.valueobjects.Email;
import com.emiliano.friendreminder.domain.valueobjects.Mobile;

/**
 * MessageService interface provides the contract for sending messages through
 * various channels.
 * 
 * @author Emiliano Pessoa
 */
public interface MessageService {

	/**
	 * Sends an SMS message.
	 * 
	 * @param mobile  The Mobile number to send the message to.
	 * @param subject The subject of the message (usually empty).
	 * @param body    The body of the message.
	 */
	void sendSMS(Mobile mobile, String subject, String body);

	/**
	 * Sends an email message.
	 * 
	 * @param email   The email address to send the message to.
	 * @param subject The subject of the email.
	 * @param body    The body of the email.
	 */
	void sendEmail(Email email, String subject, String body);
}
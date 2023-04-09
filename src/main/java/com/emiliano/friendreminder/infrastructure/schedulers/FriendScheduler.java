/**
 * 
 */
package com.emiliano.friendreminder.infrastructure.schedulers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.emiliano.friendreminder.domain.services.FriendService;

/**
 * A scheduler that runs once every 24 hours to check for upcoming birthdays and
 * send birthday messages to friends. Uses the {@link FriendService} to send the
 * messages.
 * 
 * The first call occurs 3 seconds after application start.
 * 
 * @author Emiliano Pessoa
 */
@Component
public class FriendScheduler {

	private final FriendService friendService;
	private static final Logger logger = Logger.getLogger(FriendScheduler.class.getName());

	/**
	 * Constructs a new instance of {@link FriendScheduler} with the specified
	 * {@link FriendService}.
	 *
	 * @param friendService the {@link FriendService} to use for sending birthday
	 *                      messages
	 */
	@Autowired
	public FriendScheduler(FriendService friendService) {
		this.friendService = friendService;
	}

	/**
	 * Runs the birthday message check using the {@link FriendService}. Scheduled to
	 * run every 24 hours.
	 */
	@Scheduled(fixedDelay = 24 * 60 * 60 * 1000, initialDelay = 3000)
	public void checkBirthdays() {
		logger.info("Checking Birthday messages.");
		friendService.sendBirthdayMessages();
		logger.info("Birthday messages check completed.");
	}
}

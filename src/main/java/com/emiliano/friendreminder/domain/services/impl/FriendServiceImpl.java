package com.emiliano.friendreminder.domain.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.emiliano.friendreminder.domain.entities.Friend;
import com.emiliano.friendreminder.domain.repositories.FriendRepository;
import com.emiliano.friendreminder.domain.services.FriendService;
import com.emiliano.friendreminder.domain.services.MessageService;

/**
 * Implements the FriendService interface and provides functionality for finding
 * friends with birthdays today and sending birthday messages and reminders.
 * 
 * @author Emiliano Pessoa
 */
public class FriendServiceImpl implements FriendService {

	private List<FriendRepository> repositories = null;
	private MessageService messageService = null;

	/**
	 * Creates a new instance of FriendServiceImpl.
	 * 
	 * @param messageService The message service to use for sending birthday
	 *                       messages and reminders.
	 * @param repositories   The repositories to search for friends.
	 */
	public FriendServiceImpl(MessageService messageService, FriendRepository... repositories) {
		this.repositories = List.of(repositories);
		this.messageService = messageService;
	}

	/**
	 * Returns a list of all friends in all repositories.
	 * 
	 * @return A list of all friends.
	 */
	@Override
	public List<Friend> findAllFriends() {
		List<Friend> friendList = new ArrayList<>();
		repositories.forEach(r -> {
			// Find all friends in the repository, and add any new elements to friendList
			r.findAll().stream().filter(f -> !friendList.contains(f)).forEach(friendList::add);
		});
		return friendList;
	}

	/**
	 * Returns a list of friends with birthdays today.
	 * 
	 * @return A list of friends with birthdays today.
	 */
	@Override
	public List<Friend> findTodayBirthdays() {
		int month = LocalDate.now().getMonthValue();
		int year = LocalDate.now().getYear();

		List<Friend> birthdayList = new ArrayList<>();
		repositories.forEach(r -> {
			// Filter the list of friends by those who celebrate their birthday in this date
			// and add to the birthday list if not already present.
			r.findByMonthOfBirth(month).stream()
					.filter(f -> !birthdayList.contains(f)
							&& f.getDateOfBirth().getCelebrationDate(year).equals(LocalDate.now()))
					.forEach(birthdayList::add);
		});

		return birthdayList;
	}

	/**
	 * Sends birthday messages to friends with birthdays today and birthday
	 * reminders to other friends.
	 */
	@Override
	public void sendBirthdayMessages() {
		List<Friend> birthdayFriends = findTodayBirthdays();
		if (Optional.ofNullable(birthdayFriends).isPresent()) {
			List<Friend> allFriends = findAllFriends();
			birthdayFriends.forEach(f -> {
				sendBirthdayMessage(f);
				// Remove the birthday friend.
				List<Friend> reminderFriends = allFriends.stream().filter(rf -> !rf.equals(f)).toList();
				sendBirthdayReminder(f, reminderFriends);
			});
		}
	}

	/**
	 * Sends a birthday message to the given friend.
	 * 
	 * @param birthdayFriend The friend to send a birthday message to.
	 */
	private void sendBirthdayMessage(Friend birthdayFriend) {
		String subject = "Happy birthday!";
		String body = String.format("Happy birthday, dear %s!", birthdayFriend.getFirstName());

		messageService.sendEmail(birthdayFriend.getEmail(), subject, body);
		messageService.sendSMS(birthdayFriend.getMobile(), subject, body);
	}

	/**
	 * Sends a birthday reminder to the given friends.
	 * 
	 * @param birthdayFriend   The friend who has a birthday.
	 * @param remindersFriends The friends to send reminders to.
	 */
	private void sendBirthdayReminder(Friend birthdayFriend, List<Friend> remindersFriends) {
		String subject = "Birthday reminder";
		String body = "Dear %s,\n" + "Today is %s %s's birthday. Don't forget to send a message!";

		remindersFriends.forEach(f -> {
			String nbody = String.format(body, f.getFirstName(), birthdayFriend.getFirstName(),
					birthdayFriend.getLastName());
			messageService.sendEmail(f.getEmail(), subject, nbody);
			messageService.sendSMS(f.getMobile(), subject, nbody);
		});
	}

}
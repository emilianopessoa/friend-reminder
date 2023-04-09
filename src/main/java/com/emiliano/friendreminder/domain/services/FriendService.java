package com.emiliano.friendreminder.domain.services;

import java.util.List;

import com.emiliano.friendreminder.domain.entities.Friend;

/**
 * FriendService interface provides the contract for managing friends and
 * sending birthday messages and reminders.
 * 
 * @author Emiliano Pessoa
 */
public interface FriendService {

	/**
	 * Finds all friends.
	 * 
	 * @return A list of friends.
	 */
	List<Friend> findAllFriends();

	/**
	 * Finds all friends who have birthdays today.
	 * 
	 * @return A list of friends with birthdays today.
	 */
	List<Friend> findTodayBirthdays();

	/**
	 * Sends birthday messages to friends with birthdays today and birthday
	 * reminders to other friends.
	 */
	void sendBirthdayMessages();
}
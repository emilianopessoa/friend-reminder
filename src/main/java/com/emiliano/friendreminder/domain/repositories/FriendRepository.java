package com.emiliano.friendreminder.domain.repositories;

import java.util.List;

import com.emiliano.friendreminder.domain.entities.Friend;

/**
 * FriendRepository interface provides the contract for managing {@link Friend}.
 * 
 * @author Emiliano Pessoa
 */
public interface FriendRepository {

	/**
	 * Finds all friends who have birthdays on the specified month.
	 *
	 * @param month The month of the year.
	 * @return A list of friends with birthdays on the specified day and month.
	 */
	List<Friend> findByMonthOfBirth(int month);

	/**
	 * Retrieves all friends from the data source.
	 *
	 * @return A list of all friends.
	 */
	List<Friend> findAll();

}

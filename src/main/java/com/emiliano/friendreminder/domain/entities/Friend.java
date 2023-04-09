package com.emiliano.friendreminder.domain.entities;

import java.util.Objects;

import com.emiliano.friendreminder.domain.valueobjects.DateOfBirth;
import com.emiliano.friendreminder.domain.valueobjects.Email;
import com.emiliano.friendreminder.domain.valueobjects.Mobile;

/**
 * Represents a friend entity in the domain model.
 * 
 * @author Emiliano Pessoa
 */
public class Friend {

	private final String firstName;
	private final String lastName;
	private final DateOfBirth dateOfBirth;
	private final Email email;
	private final Mobile mobile;

	/**
	 * Creates a new instance of the {@code Friend} class.
	 *
	 * @param firstName   the first name of the friend (required)
	 * @param lastName    the last name of the friend (required)
	 * @param dateOfBirth the date of birth of the friend (required)
	 * @param email       the email address of the friend (required)
	 * @param mobile      the mobile number of the friend (required)
	 * @throws IllegalArgumentException if any of the required arguments is null
	 */
	public Friend(String firstName, String lastName, DateOfBirth dateOfBirth, Email email, Mobile mobile) {
		Objects.requireNonNull(firstName, "First name cannot be null.");
		Objects.requireNonNull(lastName, "Last name cannot be null.");
		Objects.requireNonNull(dateOfBirth, "Date of birth cannot be null.");
		Objects.requireNonNull(email, "Email address cannot be null.");
		Objects.requireNonNull(mobile, "Mobile number cannot be null.");

		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.mobile = mobile;
	}

	/**
	 * Returns the first name of the friend.
	 *
	 * @return the first name of the friend
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns the last name of the friend.
	 *
	 * @return the last name of the friend
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Returns the date of birth of the friend.
	 *
	 * @return the date of birth of the friend
	 */
	public DateOfBirth getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Returns the email address of the friend.
	 *
	 * @return the email address of the friend
	 */
	public Email getEmail() {
		return email;
	}

	/**
	 * Returns the mobile number of the friend.
	 *
	 * @return the mobile number of the friend
	 */
	public Mobile getMobile() {
		return mobile;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Friend))
			return false;
		Friend friend = (Friend) o;
		return Objects.equals(firstName, friend.firstName) && Objects.equals(lastName, friend.lastName)
				&& Objects.equals(dateOfBirth, friend.dateOfBirth) && Objects.equals(email, friend.email)
				&& Objects.equals(mobile, friend.mobile);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, dateOfBirth, email, mobile);
	}
}
package com.emiliano.friendreminder.domain.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.emiliano.friendreminder.domain.valueobjects.DateOfBirth;
import com.emiliano.friendreminder.domain.valueobjects.Email;
import com.emiliano.friendreminder.domain.valueobjects.Mobile;

/**
 * Builder class for the {@link Friend} entity.
 * 
 * @author Emiliano Pessoa
 */
public class FriendBuilder {

	private String firstName;
	private String lastName;
	private String dateOfBirthString;
	private String dateOFBirthFormat;
	private String emailString;
	private String mobileString;

	/**
	 * Sets the first name of the friend.
	 *
	 * @param firstName The first name.
	 * @return This builder instance.
	 */
	public FriendBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	/**
	 * Sets the last name of the friend.
	 *
	 * @param lastName The last name.
	 * @return This builder instance.
	 */
	public FriendBuilder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	/**
	 * Sets the date of birth of the friend.
	 *
	 * @param dateOfBirthString The date of birth as a string.
	 * @param dateOFBirthFormat The format of date.
	 * @return This builder instance.
	 */
	public FriendBuilder setDateOfBirth(String dateOfBirthString, String dateOFBirthFormat) {
		this.dateOfBirthString = dateOfBirthString;
		this.dateOFBirthFormat = dateOFBirthFormat;
		return this;
	}

	/**
	 * Sets the date of birth of the friend.
	 *
	 * @param dateOfBirth The date of birth as a {@link LocalDate}.
	 * @return This builder instance.
	 */
	public FriendBuilder setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOFBirthFormat = "yyyy/MM/dd";
		this.dateOfBirthString = dateOfBirth.format(DateTimeFormatter.ofPattern(dateOFBirthFormat));
		return this;
	}

	/**
	 * Sets the email address of the friend.
	 *
	 * @param emailString The email address as a string.
	 * @return This builder instance.
	 */
	public FriendBuilder setEmail(String emailString) {
		this.emailString = emailString;
		return this;
	}

	/**
	 * Sets the mobile phone number of the friend.
	 *
	 * @param mobileString The mobile phone number as a string.
	 * @return This builder instance.
	 */
	public FriendBuilder setMobile(String mobileString) {
		this.mobileString = mobileString;
		return this;
	}

	/**
	 * Builds a new {@link Friend} instance from the set parameters.
	 *
	 * @return A new {@link Friend} instance.
	 * @throws IllegalArgumentException If any of the required parameters are
	 *                                  missing or invalid.
	 */
	public Friend build() throws IllegalArgumentException {
		DateOfBirth dateOfBirth = new DateOfBirth(dateOfBirthString, dateOFBirthFormat);
		Email email = new Email(emailString);
		Mobile mobile = new Mobile(mobileString);
		return new Friend(firstName, lastName, dateOfBirth, email, mobile);
	}
}
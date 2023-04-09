package com.emiliano.friendreminder.domain.valueobjects;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 
 * Represents an email address with validation.
 * 
 * @author Emiliano Pessoa
 */
public class Email {

	private final String value;

	/**
	 * Creates a new instance of the Email class.
	 * 
	 * @param email The email address to be validated and set.
	 * @throws IllegalArgumentException if the email address is invalid.
	 */
	public Email(String email) {
		if (!isValidEmail(email)) {
			throw new IllegalArgumentException("Invalid email address: " + email);
		}
		this.value = email;
	}

	/**
	 * Gets the email address value of the instance.
	 * 
	 * @return The email address value of the instance.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Checks whether a given email is valid or not.
	 * 
	 * @param email The email to be validated.
	 * @return True if the email is valid, false otherwise.
	 */
	public static boolean isValidEmail(String email) {
		if (email != null) {
			// Validate email using regular expression
			Pattern pattern = Pattern.compile("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$");
			return pattern.matcher(email).matches();
		}
		return false;
	}

	@Override
	public String toString() {
		return this.value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Email)) {
			return false;
		}
		Email other = (Email) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}

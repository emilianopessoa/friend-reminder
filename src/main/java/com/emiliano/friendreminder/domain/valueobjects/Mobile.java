package com.emiliano.friendreminder.domain.valueobjects;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 
 * Represents a mobile number with validation.
 * 
 * @author Emiliano Pessoa
 */
public class Mobile {
	private final String value;

	/**
	 * Creates a new instance of the Mobile class.
	 * 
	 * @param mobile The mobile number to be validated and set.
	 * @throws IllegalArgumentException if the mobile number is invalid.
	 */
	public Mobile(String value) {
		if (!isValidMobileNumber(value)) {
			throw new IllegalArgumentException("Invalid mobile number");
		}
		this.value = value;
	}

	/**
	 * Gets the mobile number value of the instance.
	 * 
	 * @return The mobile number value of the instance.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Checks whether a given mobile number is valid or not.
	 * 
	 * Example of valid numbers:<br>
	 * +1 (123) 456-7890<br>
	 * +44 20 1234 5678<br>
	 * 0034 912345678<br>
	 * 091 123 45 67<br>
	 * +55 11 98765-4321<br>
	 * +61 2 9876 5432<br>
	 * +971 50 123 4567<br>
	 * +852 6123 4567<br>
	 * 
	 * @param mobileNumber The mobile number to be validated.
	 * @return True if the mobile number is valid, false otherwise.
	 */
	private static boolean isValidMobileNumber(String mobileNumber) {
		if (mobileNumber != null) {
			// Validate mobile number using regular expression
			Pattern pattern = Pattern
					.compile("^(?:\\+\\d{1,3}|0\\d{1,3}|00\\d{1,2})?(?:\\s?\\(\\d+\\))?(?:[-\\/\\s.]|\\d)+$");
			return pattern.matcher(mobileNumber).matches();
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
		if (!(obj instanceof Mobile)) {
			return false;
		}
		Mobile other = (Mobile) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
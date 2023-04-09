package com.emiliano.friendreminder.domain.valueobjects;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * 
 * Represents a date of birth with validation.
 * 
 * @author Emiliano Pessoa
 */
public class DateOfBirth {

	private final LocalDate value;

	/**
	 * Creates a new instance of the DateOfBirth class.
	 * 
	 * @param dateOfBirth The date of birth to be validated and set.
	 * @throws IllegalArgumentException if the date of birth is invalid.
	 */
	public DateOfBirth(LocalDate dateOfBirth) {
		if (!isValidDate(dateOfBirth)) {
			throw new IllegalArgumentException("Invalid date of birth: " + dateOfBirth);
		}
		this.value = dateOfBirth;
	}

	/**
	 * Creates a new instance of the DateOfBirth class.
	 * 
	 * @param dateOfBirthStr The string representation of the date of birth.
	 * @param format         The format of the string representation.
	 * @throws IllegalArgumentException if the date format is invalid or the date of
	 *                                  birth is invalid.
	 */
	public DateOfBirth(String dateOfBirthStr, String format) {
		LocalDate dateOfBirth = null;
		try {
			dateOfBirth = LocalDate.parse(dateOfBirthStr, DateTimeFormatter.ofPattern(format));
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid date format: " + dateOfBirthStr);
		}
		if (!isValidDate(dateOfBirth)) {
			throw new IllegalArgumentException("Invalid date of birth: " + dateOfBirth);
		}
		this.value = dateOfBirth;
	}

	/**
	 * Gets the date of birth value of the instance.
	 * 
	 * @return The date of birth value of the instance.
	 */
	public LocalDate getValue() {
		return value;
	}

	/**
	 * Calculates the celebration date of birth for a given date of birth. If the
	 * date of birth falls on February 29th, the celebration date will be February
	 * 28th,except in leap years when it will be on February 29th.
	 * 
	 * @return The celebration date of birth.
	 */
	public LocalDate getCelebrationDate(int year) {
		LocalDate birthday = value;
		// Check if the birthday falls on February 29th
		if (birthday.getMonthValue() == 2 && birthday.getDayOfMonth() == 29) {
			// Return February 29th if it's a leap year, otherwise return February 28th
			if (Year.of(year).isLeap()) {
				return birthday.withYear(year);
			} else {
				return birthday.withDayOfMonth(28).withYear(year);
			}
		}

		// Return the birthday as is, with the given year
		return birthday.withYear(year);
	}

	/**
	 * Checks whether a given date of birth is valid or not. A valid date of birth
	 * is a date that is not in the future and represents an age between 0 and 150
	 * years.
	 * 
	 * @param dateOfBirth The date of birth to be validated.
	 * @return True if the date of birth is valid, false otherwise.
	 */
	public boolean isValidDate(LocalDate dateOfBirth) {
		try {
			LocalDate now = LocalDate.now();
			// check that the date of birth is not in the future
			if (dateOfBirth.isAfter(now)) {
				return false;
			}
			// check that the age is between 0 and 150 years
			int age = Period.between(dateOfBirth, now).getYears();
			if (age < 0 || age > 150) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return value.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof DateOfBirth)) {
			return false;
		}
		DateOfBirth other = (DateOfBirth) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
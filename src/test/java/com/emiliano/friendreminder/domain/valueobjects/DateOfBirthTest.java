package com.emiliano.friendreminder.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * 
 * This class provides test cases for {@link DateOfBirth}.
 * 
 * @author Emiliano Pessoa
 *
 */
class DateOfBirthTest {

	/**
	 * Tests the calculation of the celebration date for a given date of birth.
	 */
	@Test
	void celebrationDateTest() {
		// Create a new DateOfBirth with the date of birth set to February 29th, 1988.
		DateOfBirth dateOfBirth = new DateOfBirth(LocalDate.of(1988, 2, 29));

		// Test when the celebration date of birth is correctly calculated for a leap year.
		assertEquals(29, dateOfBirth.getCelebrationDate(2020).getDayOfMonth());

		// Test when the celebration date of birth is correctly calculated for not a leap year.
		assertEquals(28, dateOfBirth.getCelebrationDate(1999).getDayOfMonth());

	}

	/**
	 * Tests the validation of a given date of birth.
	 */
	@Test
	void isValidDateTest() {
		// Test when age is over 150
		assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(LocalDate.of(1800, 1, 1)));

		// Test when the birthday is greater then today.
		assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(LocalDate.now().plusYears(1)));

		// Test when the birthday input is invalid.
		assertThrows(IllegalArgumentException.class, () -> new DateOfBirth("2020/00/10", "yyyy/MM/dd"));

		// Test when the birthday input is valid.
		DateOfBirth birthday = new DateOfBirth("1985/03/10", "yyyy/MM/dd");
		assertEquals(new DateOfBirth("1985/03/10", "yyyy/MM/dd"), birthday);
	}
}

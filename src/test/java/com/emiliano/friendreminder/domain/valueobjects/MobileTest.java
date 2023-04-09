package com.emiliano.friendreminder.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * 
 * This class provides test cases for {@link Mobile}.
 * 
 * @author Emiliano Pessoa
 *
 */
class MobileTest {

	/**
	 * Tests the validation of an mobile number.
	 */
	@Test
	void invalidMobileNumberTest() {
		// Test when a null mobile number is provided.
		assertThrows(IllegalArgumentException.class, () -> new Mobile(null));

		// Test when an empty mobile number is provided.
		assertThrows(IllegalArgumentException.class, () -> new Mobile(""));

		// Test when an mobile number is invalid.
		assertThrows(IllegalArgumentException.class, () -> new Mobile("123d"));

		// Test when an mobile number is valid.
		Mobile mobile = new Mobile("1234567890");
		assertEquals("1234567890", mobile.getValue());
	}

}

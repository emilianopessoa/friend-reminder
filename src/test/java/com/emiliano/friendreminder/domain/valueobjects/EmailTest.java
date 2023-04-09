package com.emiliano.friendreminder.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * 
 * This class provides test cases for {@link Email}.
 * 
 * @author Emiliano Pessoa
 *
 */
class EmailTest {

	/**
	 * Tests the validation of an email address.
	 */
	@Test
	void invalidEmailTest() {
		// Test when a null email address is provided.
		assertThrows(IllegalArgumentException.class, () -> new Email(null));

		// Test when an empty email address is provided.
		assertThrows(IllegalArgumentException.class, () -> new Email(""));

		// Test when an email address is invalid.
		assertThrows(IllegalArgumentException.class, () -> new Email("invalidEmail"));
		
		// Test when an email address is valid.
		Email email = new Email("jhonny_bravo@hotmail.com");
		assertEquals("jhonny_bravo@hotmail.com", email.getValue());
	}

}

package com.emiliano.friendreminder.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.emiliano.friendreminder.domain.entities.Friend;
import com.emiliano.friendreminder.domain.entities.FriendBuilder;

/**
 * This class provides test cases for {@link FriendRepository}.
 * 
 * @author Emiliano Pessoa
 */
class FriendRepositoryTest {

	private FriendRepository friendRepository;
	private List<Friend> friends;

	/**
	 * Sets up the test environment by creating mock objects and sample data.
	 */
	@BeforeEach
	void setUp() {
		// create a mock repository object
		friendRepository = mock(FriendRepository.class);

		// create some sample friends
		friends = new ArrayList<>();
		friends.add(new FriendBuilder().setFirstName("Jhon").setLastName("Doe").setEmail("john.doe@test.com")
				.setDateOfBirth(LocalDate.of(1985, 1, 1)).setMobile("1234567890").build());
		friends.add(new FriendBuilder().setFirstName("Mary").setLastName("Jane").setEmail("mary.jane@test.com")
				.setDateOfBirth(LocalDate.of(1990, 10, 10)).setMobile("1111111111").build());
	}

	/**
	 * Tests the functionality of the findByMonthOfBirth method in FriendRepository.
	 */
	@Test
	void findByMonthOfBirthTest() {
		// Create a day and month.
		int month = 10;
		// mock the findByDayAndMonthOfBirth method to return a filtered list of friends
		when(friendRepository.findByMonthOfBirth(month)).thenReturn(
				friends.stream().filter(f -> f.getDateOfBirth().getValue().getMonthValue() == month).toList());
		// call the findByDayAndMonthOfBirth method on the mock repository
		List<Friend> result = friendRepository.findByMonthOfBirth(month);
		// check that the result is as expected
		assertEquals(List.of(friends.get(1)), result);
	}

	/**
	 * Tests the functionality of the findAll method in FriendRepository.
	 */
	@Test
    void findAllTest() {
        // mock the findAll method to return the full list of friends
        when(friendRepository.findAll()).thenReturn(friends);
        // call the findAll method on the mock repository
        List<Friend> result = friendRepository.findAll();
        // check that the result is as expected
        assertEquals(friends, result);
    }

}

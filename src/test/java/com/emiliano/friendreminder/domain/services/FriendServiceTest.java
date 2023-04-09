package com.emiliano.friendreminder.domain.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.emiliano.friendreminder.domain.entities.Friend;
import com.emiliano.friendreminder.domain.entities.FriendBuilder;
import com.emiliano.friendreminder.domain.repositories.FriendRepository;
import com.emiliano.friendreminder.domain.valueobjects.Email;

/**
 * This class provides test cases for {@link FriendService}.
 * 
 * @author Emiliano Pessoa
 */
@TestMethodOrder(OrderAnnotation.class)
class FriendServiceTest {

	private static FriendService friendService;
	private static FriendRepository friendRepository;
	private static MessageService messageService;
	private static List<Friend> friends;

	/**
	 * Sets up the test environment by creating mock objects and sample data.
	 */
	@BeforeAll
	static void setUp() {
		// create a mock friend service object
		friendService = mock(FriendService.class);
		// create a mock repository object
		friendRepository = mock(FriendRepository.class);
		// create a mock message service object
		messageService = mock(MessageService.class);

		// create some sample friends
		friends = new ArrayList<>();
		friends.add(new FriendBuilder().setFirstName("Jhon").setLastName("Doe").setEmail("john.doe@test.com")
				.setDateOfBirth(LocalDate.of(1985, 1, 1)).setMobile("1234567890").build());
		friends.add(new FriendBuilder().setFirstName("Mary").setLastName("Jane").setEmail("mary.jane@test.com")
				.setDateOfBirth(LocalDate.of(1990, 10, 10)).setMobile("1111111111").build());
		// Birthday friends of the day
		friends.add(new FriendBuilder().setFirstName("Jhonny").setLastName("Mac").setEmail("jhonny.mac@test.com")
				.setDateOfBirth(LocalDate.now().minusYears(25)).setMobile("2222222222").build());
		friends.add(new FriendBuilder().setFirstName("Janete").setLastName("Grape").setEmail("janete.grape@test.com")
				.setDateOfBirth(LocalDate.now().minusYears(30)).setMobile("3333333333").build());

	}

	/**
	 * Tests the functionality of the findAllFriends method in FriendService.
	 */
	@Test
	@Order(1)
	void findAllFriendsTest() {
		// mock the repository findAll method to return the full list of friends.
		when(friendRepository.findAll()).thenReturn(friends);
		
		// Get the full list of friends from the repository.
		List<Friend> friendList = friendRepository.findAll();
		
		// Mock the service findAll method to return the full list of friends.
		when(friendService.findAllFriends()).thenReturn(friendList);
		
		// Get the full list of friends from the service.
		List<Friend> result = friendService.findAllFriends();
		
		// check that the result is as expected.
		assertEquals(friends, result);
	}

	/**
	 * Tests the functionality of the findTodayBirthdays method in FriendService.
	 */
	@Test
	@Order(2)
	void findTodayBirthdaysTest() {
		int year = LocalDate.now().getYear();
		int month = LocalDate.now().getMonthValue();

		// Mock the repository findByMonthOfBirth method to return a list of today
		// birthday friends
		when(friendRepository.findByMonthOfBirth(month)).thenReturn(
				friends.stream().filter(f -> f.getDateOfBirth().getValue().getMonthValue() == month).toList());

		// Get the list of the birthdays of the month.
		List<Friend> friendList = friendRepository.findByMonthOfBirth(month);

		// Mock the service findTodayBirthdays method to return a list of today birthday
		// friends.
		when(friendService.findTodayBirthdays()).thenReturn(friendList.stream()
				.filter(f -> f.getDateOfBirth().getCelebrationDate(year).equals(LocalDate.now())).toList());

		// Get the list of today birthday friends from the service.
		List<Friend> result = friendService.findTodayBirthdays();

		// Check that the result is as expected.
		Assertions.assertEquals(2, result.size());
	}

	/**
	 * Tests the functionality of the sendBirthdayMessages method in FriendService
	 * and verifies that the messageService methods are called with correct arguments.
	 */
	@Test
	@Order(3)
	void sendBirthdayMessagesTest() {

		String happyBirhday = "Happy birthday";
		String reminder = "Birthday reminder";

		List<Friend> birthdayFriends = friendService.findTodayBirthdays();

		if (Optional.ofNullable(birthdayFriends).isPresent()) {
			List<Friend> allFriends = friendService.findAllFriends();

			birthdayFriends.forEach(f -> {
				messageService.sendEmail(f.getEmail(), happyBirhday, "");
				messageService.sendSMS(f.getMobile(), happyBirhday, "");
				// Remove the birthday friend.
				List<Friend> reminderFriends = allFriends.stream().filter(rf -> !rf.equals(f)).toList();
				reminderFriends.forEach(af -> {
					messageService.sendEmail(af.getEmail(), reminder, "");
					messageService.sendSMS(af.getMobile(), reminder, "");
				});
			});
		}

		// Checks that each birthday person has received congratulations.
		verify(messageService, times(1)).sendEmail(eq(new Email("jhonny.mac@test.com")), contains(happyBirhday),anyString());
		verify(messageService, times(1)).sendEmail(eq(new Email("janete.grape@test.com")), contains(happyBirhday),anyString());

		// Checks that each friend has received 2 reminder messages.
		verify(messageService, times(2)).sendEmail(eq(new Email("john.doe@test.com")), contains(reminder), anyString());
		verify(messageService, times(2)).sendEmail(eq(new Email("mary.jane@test.com")), contains(reminder),anyString());

		// if is birthday person should receive 1 reminder.
		verify(messageService, times(1)).sendEmail(eq(new Email("jhonny.mac@test.com")), contains(reminder), anyString());
		verify(messageService, times(1)).sendEmail(eq(new Email("janete.grape@test.com")), contains(reminder), anyString());

		// Check if the sendSMS was called correctly.
		verify(messageService, times(8)).sendSMS(any(), anyString(), anyString());
	}

}

package com.emiliano.friendreminder.domain.services.impl;

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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.emiliano.friendreminder.domain.entities.Friend;
import com.emiliano.friendreminder.domain.entities.FriendBuilder;
import com.emiliano.friendreminder.domain.repositories.FriendRepository;
import com.emiliano.friendreminder.domain.services.FriendService;
import com.emiliano.friendreminder.domain.services.MessageService;
import com.emiliano.friendreminder.domain.valueobjects.Email;

/**
 * This class provides test cases for {@link FriendServiceImpl}.
 * 
 * @author Emiliano Pessoa
 */
class FriendServiceImplTest {

	private static FriendService friendService = null;
	private static List<FriendRepository> repositories = null;
	private static MessageService messageService;

	/**
	 * Sets up the test environment by creating mock objects and sample data.
	 */
	@BeforeAll
	static void setUp() {

		// create some sample friends to repository 1
		List<Friend> friends1 = new ArrayList<>();
		friends1.add(new FriendBuilder().setFirstName("Jhon").setLastName("Doe").setEmail("john.doe@test.com")
				.setDateOfBirth(LocalDate.of(1985, 1, 1)).setMobile("1234567890").build());
		friends1.add(new FriendBuilder().setFirstName("Mary").setLastName("Jane").setEmail("mary.jane@test.com")
				.setDateOfBirth(LocalDate.of(1990, 10, 10)).setMobile("1111111111").build());
		// Birthday friends of the day
		friends1.add(new FriendBuilder().setFirstName("Jhonny").setLastName("Mac").setEmail("jhonny.mac@test.com")
				.setDateOfBirth(LocalDate.now().minusYears(25)).setMobile("2222222222").build());
		friends1.add(new FriendBuilder().setFirstName("Janete").setLastName("Grape").setEmail("janete.grape@test.com")
				.setDateOfBirth(LocalDate.now().minusYears(30)).setMobile("3333333333").build());

		// create some sample friends to repository 2
		List<Friend> friends2 = new ArrayList<>();
		friends2.add(new FriendBuilder().setFirstName("Grace").setLastName("Kelly").setEmail("grace.kelly@test.com")
				.setDateOfBirth(LocalDate.of(1999, 5, 5)).setMobile("4444444444").build());
		// Birthday friends of the day
		friends2.add(new FriendBuilder().setFirstName("Ricardo").setLastName("Smith").setEmail("ricardo.smith@test.com")
				.setDateOfBirth(LocalDate.now().minusYears(40)).setMobile("5555555555").build());
		// Add some friends to repository 2 who are already present in repository 1 to
		// ensure that the duplicates are handled correctly.
		friends2.add(new FriendBuilder().setFirstName("Mary").setLastName("Jane").setEmail("mary.jane@test.com")
				.setDateOfBirth(LocalDate.of(1990, 10, 10)).setMobile("1111111111").build());
		friends2.add(new FriendBuilder().setFirstName("Janete").setLastName("Grape").setEmail("janete.grape@test.com")
				.setDateOfBirth(LocalDate.now().minusYears(30)).setMobile("3333333333").build());

		// Create mock friends repositories
		FriendRepository repository1 = mock(FriendRepository.class);
		FriendRepository repository2 = mock(FriendRepository.class);

		// Mock findAll method with each friend list.
		when(repository1.findAll()).thenReturn(friends1);
		when(repository2.findAll()).thenReturn(friends2);

		// Mock findByMonthOfBirth method for each friend who was born in this month.
		int month = LocalDate.now().getMonthValue();
		when(repository1.findByMonthOfBirth(month)).thenReturn(
				friends1.stream().filter(f -> f.getDateOfBirth().getValue().getMonthValue() == month).toList());
		when(repository2.findByMonthOfBirth(month)).thenReturn(
				friends2.stream().filter(f -> f.getDateOfBirth().getValue().getMonthValue() == month).toList());

		// Add the created repositories to the list.
		repositories = new ArrayList<>();
		repositories.add(repository1);
		repositories.add(repository2);

		// create a mock message service object.
		messageService = mock(MessageService.class);

		// Create friend service instance with mock repositories and messageService
		friendService = new FriendServiceImpl(messageService, repository1, repository2);
	}

	/**
	 * Tests the functionality of the findAllFriends method in FriendService.
	 */
	@Test
	@Order(1)
	void findAllFriendsTest() {
		// Call findAllFriends() method
		List<Friend> result = friendService.findAllFriends();

		// Checks if the result has 6 different friends
		assertEquals(6, result.size());
	}

	/**
	 * Tests the functionality of the findTodayBirthdays method in FriendService.
	 */
	@Test
	@Order(2)
	void findTodayBirthdaysTest() {
		// Call findTodayBirthdays() method
		List<Friend> result = friendService.findTodayBirthdays();

		// Verify that the result contains only the friends with a birthday today
		assertEquals(3, result.size());
	}

	/**
	 * Tests the functionality of the sendBirthdayMessages method in FriendService
	 * and verifies that the messageService methods are called with correct
	 * arguments.
	 */
	@Test
	@Order(3)
	void sendBirthdayMessagesTest() {
		// Call sendBirthdayMessages() method
		friendService.sendBirthdayMessages();

		String happyBirhday = "Happy birthday";
		String reminder = "reminder";

		// Check that each birthday person has received congratulations.
		verify(messageService, times(1)).sendEmail(eq(new Email("jhonny.mac@test.com")), contains(happyBirhday),
				anyString());
		verify(messageService, times(1)).sendEmail(eq(new Email("janete.grape@test.com")), contains(happyBirhday),
				anyString());
		verify(messageService, times(1)).sendEmail(eq(new Email("ricardo.smith@test.com")), contains(happyBirhday),
				anyString());

		// Check that the other friends has received the reminder for this 3 birthdays.
		verify(messageService, times(3)).sendEmail(eq(new Email("john.doe@test.com")), contains(reminder), anyString());
		verify(messageService, times(3)).sendEmail(eq(new Email("mary.jane@test.com")), contains(reminder),
				anyString());
		verify(messageService, times(3)).sendEmail(eq(new Email("grace.kelly@test.com")), contains(reminder),
				anyString());

		// if is birthday person should receive 2 reminders.
		verify(messageService, times(2)).sendEmail(eq(new Email("jhonny.mac@test.com")), contains(reminder),
				anyString());
		verify(messageService, times(2)).sendEmail(eq(new Email("janete.grape@test.com")), contains(reminder),
				anyString());
		verify(messageService, times(2)).sendEmail(eq(new Email("ricardo.smith@test.com")), contains(reminder),
				anyString());

		// Check if the sendSMS was called correctly.
		verify(messageService, times(18)).sendSMS(any(), anyString(), anyString());
	}

}

package com.emiliano.friendreminder.infrastructure.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.emiliano.friendreminder.Application;
import com.emiliano.friendreminder.domain.entities.Friend;
import com.emiliano.friendreminder.domain.services.FriendService;
import com.emiliano.friendreminder.domain.services.MessageService;
import com.emiliano.friendreminder.domain.services.impl.FriendServiceImpl;
import com.emiliano.friendreminder.domain.valueobjects.Email;
import com.emiliano.friendreminder.infrastructure.repositories.impl.SQLiteFriendRepositoryImpl;

/**
 * This class provides test cases for {@link FriendServiceImpl}.
 * 
 * These tests are separated from the domain layer to enable the use of
 * infrastructure features such as Spring Test.
 * 
 * The sample data used for testing is created and initialized in the
 * {@link Application} class. By default, a person with today's birthday is
 * added to the list.
 * 
 * @author Emiliano Pessoa
 */
@SpringBootTest
class FriendServiceImplTest {

	@Autowired
	private SQLiteFriendRepositoryImpl repository;

	private FriendService friendService;

	private MessageService messageService;

	/**
	 * Sets up the test environment by creating mock objects and sample data.
	 */
	@BeforeEach
	void setUp() {
		// create a mock message service object.
		messageService = mock(MessageService.class);

		// Cal implementation of the FriendService using only the SQLite repository. We
		// know that today is Pedro's birthday.
		friendService = new FriendServiceImpl(messageService, repository);
	}

	/**
	 * Tests the functionality of the findAllFriends method in FriendService.
	 */
	@Test
	@Order(1)
	void findAllFriendsTest() {
		// Call findAllFriends() method
		List<Friend> result = friendService.findAllFriends();

		// Checks if the result has 3 different friends
		assertEquals(3, result.size());
	}

	/**
	 * Tests the functionality of the findTodayBirthdays method in FriendService.
	 */
	@Test
	@Order(2)
	void findTodayBirthdaysTest() {
		// Call findTodayBirthdays() method
		Friend result = friendService.findTodayBirthdays().stream()
				.filter(friend -> "pedro.souza@test.com".equals(friend.getEmail().getValue())).findFirst().get();

		// Verify that the result contains only the friends with a birthday today
		assertEquals("pedro.souza@test.com", result.getEmail().getValue());
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

		verify(messageService).sendEmail(eq(new Email("pedro.souza@test.com")), anyString(), anyString());
	}

}

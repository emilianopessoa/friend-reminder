/**
 * 
 */
package com.emiliano.friendreminder.infrastructure.repositories.impl;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.emiliano.friendreminder.domain.entities.Friend;

/**
 * This class provides test cases for {@link FlatFileFriendRepositoryImpl}.
 * 
 * The sample data used for testing is already created in the friends-test.csv
 * file.
 * 
 * @author Emiliano Pessoa
 */
@SpringBootTest
public class FlatFileFriendRepositoryImplTest {

	@Autowired
	private FlatFileFriendRepositoryImpl repository;

	@Test
	void testFindByMonthOfBirth() {
		List<Friend> friends = repository.findByMonthOfBirth(4);
		Assertions.assertNotNull(friends);
		Assertions.assertEquals(1, friends.size());
	}

	@Test
	void testFindAll() {
		List<Friend> friends = repository.findAll();
		Assertions.assertNotNull(friends);
		Assertions.assertEquals(3, friends.size());
	}
}

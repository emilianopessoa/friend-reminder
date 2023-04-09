package com.emiliano.friendreminder.infrastructure;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.emiliano.friendreminder.domain.repositories.FriendRepository;
import com.emiliano.friendreminder.domain.services.FriendService;
import com.emiliano.friendreminder.domain.services.MessageService;
import com.emiliano.friendreminder.domain.services.impl.FriendServiceImpl;

/**
 * Bean configuration class for the application.
 * 
 * This class provides the bean definitions needed for the application to run.
 * 
 * It sets up the configuration for the flat file path, and initializes the
 * {@link FriendService}.
 * 
 * @author Emiliano Pessoa
 *
 */
@Configuration
public class BeanConfiguration {

	@Autowired
	Environment env;

	/**
	 * 
	 * Reads the file path from the environment property 'friend.flat.file'.
	 * 
	 * @return The Path object representing the flat file location.
	 * @throws RuntimeException If there is an error reading the CSV file.
	 */
	@Bean
	public Path getFlatFilePath() {
		return Paths.get(URI.create(env.getProperty("friend.flat.file")));
	}

	/**
	 * 
	 * Initializes the {@link FriendService} with the provided
	 * {@link MessageService} and an array of {@link FriendRepository} instances.
	 * 
	 * @param messageService The {@link MessageService} instance for sending
	 *                       messages to friends.
	 * @param repositories   An array of {@link FriendRepository} instances.
	 * @return A new {@link FriendServiceImpl} instance with the provided
	 *         dependencies.
	 */
	@Bean
	FriendService friendService(MessageService messageService, FriendRepository... repositories) {
		return new FriendServiceImpl(messageService, repositories);
	}

}

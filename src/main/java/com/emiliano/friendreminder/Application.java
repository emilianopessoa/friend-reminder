package com.emiliano.friendreminder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.emiliano.friendreminder.infrastructure.entities.FriendEntity;
import com.emiliano.friendreminder.infrastructure.repositories.SQLiteFriendEntityRepository;
import com.emiliano.friendreminder.infrastructure.schedulers.FriendScheduler;

/**
 * The main class of the Birthday Reminder application. This class is
 * responsible for initializing the Spring Boot application, setting up the
 * database repository, and creating sample data.
 * 
 * The class implements the CommandLineRunner interface, which allows it to run
 * the 'createSampleData' method once the application has started. This method
 * adds some sample friends to the SQLite database for testing purposes.
 * 
 * The class also uses the @SpringBootApplication annotation to enable Spring
 * Boot auto-configuration, and the
 * 
 * @EnableScheduling annotation to enable scheduling of the
 *                   {@link FriendScheduler} class. When initializing the
 *                   {@link FriendScheduler} scheduler, it is called every 24
 *                   hours, starting 3 seconds after the application starts.
 * 
 * @author Emiliano Pessoa
 */
@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner {

	private SQLiteFriendEntityRepository sqliteRepository;

	/**
	 * Creates a new BirthdayReminderApplication object with the specified
	 * repository.
	 * 
	 * @param repository the repository used to access friend data in the database
	 */
	@Autowired
	public Application(SQLiteFriendEntityRepository repository) {
		this.sqliteRepository = repository;
	}

	/**
	 * The main method of the application, which starts the Spring Boot application.
	 * 
	 * @param args the command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * The run method of the CommandLineRunner interface, which is called once the
	 * application has started.
	 * 
	 * @param args the command line arguments passed to the application
	 * @throws Exception if there is an error while running the method
	 */
	@Override
	public void run(String... args) throws Exception {
		createSampleData();
	}

	/**
	 * Adds some sample friends to the SQLite database for testing purposes.
	 */
	private void createSampleData() {
		List<FriendEntity> friends = new ArrayList<>();
		friends.add(new FriendEntity("Carlos", "Silva", LocalDate.of(1980, 5, 15), "carlos.silva@test.com",
				"+55 11 987654321"));
		friends.add(new FriendEntity("Ana", "Santos", LocalDate.of(1990, 9, 30), "ana.santos@test.com",
				"+55 27 912345678"));
		friends.add(new FriendEntity("Pedro", "Souza", LocalDate.now().minusYears(30), "pedro.souza@test.com",
				"+55 21 998877665"));
		// save the sample friends to the repository
		List<FriendEntity> existingFriends = sqliteRepository.findAll();
		friends.forEach(f -> {
			if (!existingFriends.contains(f)) {
				sqliteRepository.save(f);
				existingFriends.add(f);
				System.out.println("");
			}
		});
	}
}

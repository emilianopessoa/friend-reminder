package com.emiliano.friendreminder.infrastructure.repositories;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.emiliano.friendreminder.infrastructure.entities.FriendEntity;

/**
 * 
 * This class represents a repository for managing FriendEntity objects stored
 * in a CSV file.
 * 
 * @author Emiliano Pessoa
 *
 */
@Repository
public class FlatFileFriendEntityRepository {

	private final Path csvFilePath;

	/**
	 * Creates a new instance of this class.
	 * 
	 * @param csvFilePath The location of the CSV file.
	 */
	public FlatFileFriendEntityRepository(Path csvFilePath) {
		this.csvFilePath = csvFilePath;
	}

	/**
	 * Retrieves all {@link FriendEntity} from the data source.
	 *
	 * @return A list of all {@link FriendEntity}.
	 */
	public List<FriendEntity> findAll() {
		List<FriendEntity> friends = new ArrayList<>();

		try (BufferedReader reader = Files.newBufferedReader(csvFilePath)) {
			String headerLine = reader.readLine();
			List<String> headers = Arrays.asList(headerLine.split(","));
			int lastNameIndex = headers.indexOf("last_name");
			int firstNameIndex = headers.indexOf("first_name");
			int dateOfBirthIndex = headers.indexOf("date_of_birth");
			int emailIndex = headers.indexOf("email");
			int mobileIndex = headers.indexOf("mobile");

			String line;
			while ((line = reader.readLine()) != null) {
				List<String> fields = Arrays.asList(line.split(","));

				String lastName = fields.get(lastNameIndex);
				String firstName = fields.get(firstNameIndex);
				LocalDate dateOfBirth = LocalDate.parse(fields.get(dateOfBirthIndex),
						DateTimeFormatter.ofPattern("yyyy/MM/dd"));
				String email = fields.get(emailIndex);
				String mobile = mobileIndex >= 0 ? fields.get(mobileIndex) : null;

				FriendEntity friend = new FriendEntity(firstName, lastName, dateOfBirth, email, mobile);
				friends.add(friend);
			}
		} catch (IOException e) {
			throw new RuntimeException("Error reading CSV file", e);
		}

		return friends;
	}
}

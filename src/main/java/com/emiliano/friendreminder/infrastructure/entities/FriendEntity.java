package com.emiliano.friendreminder.infrastructure.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a friend infrastructure entity used for persistence with Spring
 * Data. This entity is used to store and retrieve friend data in the database.
 *
 * @author Emiliano Pessoa
 */
@Entity
public class FriendEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@Column(columnDefinition = "DATE")
	private LocalDate dateOfBirth;
	private String email;
	private String mobile;

	/**
	 * Creates a new empty FriendEntity object.
	 */
	public FriendEntity() {

	}

	/**
	 * Creates a new FriendEntity object with the specified properties.
	 *
	 * @param id          The unique ID of the friend entity.
	 * @param firstName   The first name of the friend.
	 * @param lastName    The last name of the friend.
	 * @param dateOfBirth The date of birth of the friend.
	 * @param email       The email address of the friend.
	 * @param mobile      The mobile phone number of the friend.
	 */
	public FriendEntity(String firstName, String lastName, LocalDate dateOfBirth, String email, String mobile) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.mobile = mobile;
	}

	/**
	 * Returns the id of the friend entity.
	 *
	 * @return the id of the friend entity
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id of the friend entity.
	 *
	 * @param id the id to set for the friend entity
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns the first name of the friend entity.
	 *
	 * @return the first name of the friend entity
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the friend entity.
	 *
	 * @param firstName the first name to set for the friend entity
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the friend entity.
	 *
	 * @return the last name of the friend entity
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the friend entity.
	 *
	 * @param lastName the last name to set for the friend entity
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the date of birth of the friend entity.
	 *
	 * @return the date of birth of the friend entity
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth of the friend entity.
	 *
	 * @param dateOfBirth the date of birth to set for the friend entity
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Returns the email address of the friend entity.
	 *
	 * @return the email address of the friend entity
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the friend entity.
	 *
	 * @param email the email address to set for the friend entity
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the mobile number of the friend entity.
	 *
	 * @return the mobile number of the friend entity
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the mobile number of the friend entity.
	 *
	 * @param mobile the mobile number to set for the friend entity
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
/**
 * 
 */
package com.emiliano.friendreminder.infrastructure.repositories.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emiliano.friendreminder.domain.entities.Friend;
import com.emiliano.friendreminder.domain.repositories.FriendRepository;
import com.emiliano.friendreminder.infrastructure.mappers.FriendMapper;
import com.emiliano.friendreminder.infrastructure.repositories.SQLiteFriendEntityRepository;

/**
 * Implements the {@link FriendRepository} interface with
 * {@link SQLiteFriendEntityRepository}.
 * 
 * @author Emiliano Pessoa
 */
@Component
public class SQLiteFriendRepositoryImpl implements FriendRepository {

	@Autowired
	private SQLiteFriendEntityRepository repository;

	@Override
	public List<Friend> findByMonthOfBirth(int month) {
		return FriendMapper.toDomainList(
				repository.findAll().stream().filter(f -> f.getDateOfBirth().getMonthValue() == month).toList());
	}

	@Override
	public List<Friend> findAll() {
		return FriendMapper.toDomainList(repository.findAll());
	}

}

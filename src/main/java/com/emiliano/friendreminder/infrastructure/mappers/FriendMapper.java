package com.emiliano.friendreminder.infrastructure.mappers;

import java.util.ArrayList;
import java.util.List;

import com.emiliano.friendreminder.domain.entities.Friend;
import com.emiliano.friendreminder.domain.entities.FriendBuilder;
import com.emiliano.friendreminder.infrastructure.entities.FriendEntity;

/**
 * Utility class to handle conversions between {@link Friend} domain entity and
 * {@link FriendEntity} infrastructure entity.
 */
public class FriendMapper {

	/**
	 * Converts a {@link FriendEntity} to a {@link Friend}.
	 *
	 * @param entity The {@link FriendEntity} to convert.
	 * @return The converted {@link Friend}.
	 */
	public static Friend toDomain(FriendEntity entity) {
		if (entity == null) {
			return null;
		}

		return new FriendBuilder().setFirstName(entity.getFirstName()).setLastName(entity.getLastName())
				.setDateOfBirth(entity.getDateOfBirth()).setEmail(entity.getEmail()).setMobile(entity.getMobile())
				.build();
	}

	/**
	 * Converts a list of {@link FriendEntity} to a list of {@link Friend}.
	 *
	 * @param entities The list of {@link FriendEntity} to convert.
	 * @return The converted list of {@link Friend}.
	 */
	public static List<Friend> toDomainList(List<FriendEntity> entities) {
		if (entities == null) {
			return null;
		}

		List<Friend> friends = new ArrayList<>();
		entities.forEach(e -> friends.add(toDomain(e)));
		return friends;
	}

	/**
	 * Converts a {@link Friend} to a {@link FriendEntity}.
	 *
	 * @param friend The {@link Friend} to convert.
	 * @return The converted {@link FriendEntity}.
	 */
	public static FriendEntity toInfrastructure(Friend friend) {
		if (friend == null) {
			return null;
		}

		FriendEntity friendEntity = new FriendEntity();
		friendEntity.setFirstName(friend.getFirstName());
		friendEntity.setLastName(friend.getLastName());
		friendEntity.setDateOfBirth(friend.getDateOfBirth().getValue());
		friendEntity.setEmail(friend.getEmail().getValue());
		friendEntity.setMobile(friend.getMobile().getValue());

		return friendEntity;
	}

	/**
	 * Converts a list of {@link Friend} to a list of {@link FriendEntity}.
	 *
	 * @param friend The list of {@link Friend} to convert.
	 * @return The converted list of {@link FriendEntity}.
	 */
	public static List<FriendEntity> toInfrastructureList(List<Friend> friends) {
		if (friends == null) {
			return null;
		}
		List<FriendEntity> friendsEntity = new ArrayList<>();
		friends.forEach(e -> friendsEntity.add(toInfrastructure(e)));
		return friendsEntity;
	}

}
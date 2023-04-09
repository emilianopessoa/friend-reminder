package com.emiliano.friendreminder.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.emiliano.friendreminder.infrastructure.entities.FriendEntity;

/**
 * 
 * This class represents a repository for managing {@link FriendEntity} objects
 * stored in a SQLite database.
 * 
 * @author Emiliano Pessoa
 *
 */
@Repository
public interface SQLiteFriendEntityRepository extends JpaRepository<FriendEntity, Long> {

}

package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link Repository} for handling with {@link UserRecord} entity.
 */
@Repository
public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {
}

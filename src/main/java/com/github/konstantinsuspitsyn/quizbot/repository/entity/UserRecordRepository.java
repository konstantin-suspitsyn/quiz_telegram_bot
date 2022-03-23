package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link Repository} for handling with {@link UserRecord} entity.
 */
@Repository
public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {

    @Query(value = "select sum(ur.correct) from UserRecord ur where ur.userId = ?1 ")
    int getSumOfCorrectAnswers(String chatId);

    @Query(value = "select count(*) from UserRecord ur where ur.userId = ?1 ")
    int getCountOfAllAnswers(String chatId);

    @Transactional
    @Modifying
    @Query(value = "delete from UserRecord ur where ur.userId = ?1")
    void deleteUserResults(String chatId);

}

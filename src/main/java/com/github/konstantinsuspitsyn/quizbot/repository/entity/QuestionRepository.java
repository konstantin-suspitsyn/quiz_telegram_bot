package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * {@link Repository} for handling with {@link Question} entity.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question getById(Long id);

    @Query(value = "select q from Question q " +
            "where q.id not in " +
            "(select u.questionId from UserRecord u where u.userId = ?1 )")
    List<Question> getNextQuestion(String userId);
}

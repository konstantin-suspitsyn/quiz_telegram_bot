package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link Repository} for handling with {@link Answers} entity.
 */
public interface AnswersRepository extends JpaRepository<Answers, Long> {
    List<Answers> getByQuestionId(Long id);
}

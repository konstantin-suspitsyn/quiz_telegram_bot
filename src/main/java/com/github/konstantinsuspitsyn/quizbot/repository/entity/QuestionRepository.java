package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * {@link Repository} for handling with {@link Question} entity.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question getById(Long id);
}

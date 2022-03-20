package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * {@link Repository} for handling with {@link Answers} entity.
 */
@Repository
public interface AnswersRepository extends JpaRepository<Answers, Long> {
    List<Answers> getByQuestionId(Long id);
    Optional<Answers> findById(Long id);
}

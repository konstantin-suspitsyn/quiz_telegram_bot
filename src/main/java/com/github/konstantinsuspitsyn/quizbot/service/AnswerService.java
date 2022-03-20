package com.github.konstantinsuspitsyn.quizbot.service;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.Answers;

import java.util.List;
import java.util.Optional;

public interface AnswerService {
    List<Answers> getAnswerByQuestionId(Long questionId);
    Optional<Answers> findById(Long id);
}

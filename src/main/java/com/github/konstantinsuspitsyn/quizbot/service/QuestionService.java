package com.github.konstantinsuspitsyn.quizbot.service;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.Question;
import com.github.konstantinsuspitsyn.quizbot.repository.entity.QuestionRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    Optional<Question> findById(Long id);

}

package com.github.konstantinsuspitsyn.quizbot.service;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.Question;
import com.github.konstantinsuspitsyn.quizbot.repository.entity.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionServiceRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionServiceRepository) {
        this.questionServiceRepository = questionServiceRepository;
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionServiceRepository.findById(id);
    }

    @Override
    public List<Question> getNextQuestion(String userId) {
        return questionServiceRepository.getNextQuestion(userId);
    }
}

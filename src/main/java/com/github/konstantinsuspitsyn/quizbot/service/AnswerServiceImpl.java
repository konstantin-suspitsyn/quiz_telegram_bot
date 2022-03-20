package com.github.konstantinsuspitsyn.quizbot.service;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.Answers;
import com.github.konstantinsuspitsyn.quizbot.repository.entity.AnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswersRepository answersRepository;

    @Autowired
    public AnswerServiceImpl(AnswersRepository answersRepository) {
        this.answersRepository = answersRepository;
    }

    @Override
    public List<Answers> getAnswerByQuestionId(Long questionId) {
        return answersRepository.getByQuestionId(questionId);
    }
}

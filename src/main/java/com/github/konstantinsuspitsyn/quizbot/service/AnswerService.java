package com.github.konstantinsuspitsyn.quizbot.service;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.Answers;

import java.util.List;

public interface AnswerService {
    List<Answers> getAnswerByQuestionId(Long questionId);
}

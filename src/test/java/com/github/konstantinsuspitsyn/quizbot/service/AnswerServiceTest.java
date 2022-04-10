package com.github.konstantinsuspitsyn.quizbot.service;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.AnswersRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


class AnswerServiceTest {

    @Mock
    private AnswersRepository answersRepository;

    private AutoCloseable autoCloseable;
    private AnswerService answerService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        answerService = new AnswerServiceImpl(answersRepository);
    }

    @SneakyThrows
    @AfterEach
    void tearDown(){
        autoCloseable.close();
    }

    @Test
    void shouldGetAnswerByQuestionId() {
        // when
        answerService.getAnswerByQuestionId(1L);
        // then
        verify(answersRepository).getByQuestionId(1L);
    }

    @Test
    void findById() {
        // when
        answerService.findById(1L);
        // then
        verify(answersRepository).findById(1L);
    }
}
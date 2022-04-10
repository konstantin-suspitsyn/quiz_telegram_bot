package com.github.konstantinsuspitsyn.quizbot.service;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.QuestionRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


class QuestionServiceTest {

    public static final String UID = "UID";
    public static final long ID = 1L;
    @Mock
    private QuestionRepository questionRepository;

    private AutoCloseable autoCloseable;
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        questionService = new QuestionServiceImpl(questionRepository);
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        autoCloseable.close();
    }

    @Test
    void shouldFindById() {
        // when
        questionService.findById(ID);
        // then
        verify(questionRepository).findById(ID);

    }

    @Test
    void shouldGetNextQuestion() {
        // when
        questionService.getNextQuestion(UID);
        // then
        verify(questionRepository).getNextQuestion(UID);
    }
}
package com.github.konstantinsuspitsyn.quizbot.service;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.UserRecord;
import com.github.konstantinsuspitsyn.quizbot.repository.entity.UserRecordRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


class UserRecordServiceTest {

    public static final String CHAT_ID = "chatId";
    @Mock
    private UserRecordRepository userRecordRepository;
    @Mock
    private UserRecord userRecord;

    private AutoCloseable autoCloseable;
    private UserRecordService userRecordService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userRecordService = new UserRecordServiceImpl(userRecordRepository);
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        autoCloseable.close();
    }

    @Test
    void shouldSave() {
        // when
        userRecordService.save(userRecord);
        //then
        verify(userRecordRepository).save(userRecord);
    }

    @Test
    void shouldGetSumOfCorrectAnswers() {
        // when
        userRecordService.getSumOfCorrectAnswers(CHAT_ID);
        //then
        verify(userRecordRepository).getSumOfCorrectAnswers(CHAT_ID);
    }

    @Test
    void shouldGetCountOfAllAnswers() {
        // when
        userRecordService.getCountOfAllAnswers(CHAT_ID);
        //then
        verify(userRecordRepository).getCountOfAllAnswers(CHAT_ID);
    }

    @Test
    void deleteUserResults() {
        // when
        userRecordService.deleteUserResults(CHAT_ID);
        //then
        verify(userRecordRepository).deleteUserResults(CHAT_ID);
    }
}
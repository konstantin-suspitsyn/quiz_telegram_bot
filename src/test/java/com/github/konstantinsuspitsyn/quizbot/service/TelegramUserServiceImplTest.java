package com.github.konstantinsuspitsyn.quizbot.service;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.TelegramUser;
import com.github.konstantinsuspitsyn.quizbot.repository.entity.TelegramUserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@DisplayName("Test for TelegramUserService")
class TelegramUserServiceImplTest {

    public static final String CHAT_ID = "chatId";
    @Mock
    private TelegramUserRepository telegramUserRepository;
    @Mock
    private TelegramUser telegramUser;

    private AutoCloseable autoCloseable;
    private TelegramUserServiceImpl telegramUserService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        telegramUserService = new TelegramUserServiceImpl(telegramUserRepository);
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        autoCloseable.close();
    }

    @Test
    void save() {
        // when
        telegramUserService.save(telegramUser);
        // then
        verify(telegramUserRepository).save(telegramUser);
    }

    @Test
    void retrieveAllActiveUsers() {
        // when
        telegramUserService.retrieveAllActiveUsers();
        // then
        verify(telegramUserRepository).findAllByActiveTrue();
    }

    @Test
    void findByChatID() {
        // when
        telegramUserService.findByChatID(CHAT_ID);
        // then
        verify(telegramUserRepository).findById(CHAT_ID);
    }
}
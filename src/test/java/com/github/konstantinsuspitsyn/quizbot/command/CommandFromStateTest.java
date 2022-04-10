package com.github.konstantinsuspitsyn.quizbot.command;

import com.github.konstantinsuspitsyn.quizbot.service.TelegramUserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class CommandFromStateTest {

    @Mock
    TelegramUserService telegramUserService;

    CommandFromState commandFromState;
    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        commandFromState = new CommandFromState(telegramUserService);
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        autoCloseable.close();
    }

    @Test
    void execute() {
    }

    @Test
    void retrieveCommand() {

    }
}
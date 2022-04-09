package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test for TelegramUserRepository")
@DataJpaTest
class TelegramUserRepositoryTest {

    public static final String TEST_NAME = "TestName";
    @Autowired
    private TelegramUserRepository underTest;

    private TelegramUser telegramUser;

    @BeforeEach
    public void init() {
        // given
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChatId("testChatId");
        telegramUser.setUserName(TEST_NAME);
        telegramUser.setActive(true);
        underTest.save(telegramUser);
    }

    @AfterEach
    void Clean() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindAllByActiveTrue() {

        // when
        List<TelegramUser> telegramUsers = underTest.findAllByActiveTrue();

        // then
        assertTrue(telegramUsers.size() == 1);

    }

    @Test
    void itShouldFindByUserName() {
        // when
        Optional<TelegramUser> optionalTelegramUser = underTest.findByUserName(TEST_NAME);

        // then
        assertTrue(optionalTelegramUser.isPresent());
    }

}
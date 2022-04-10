package com.github.konstantinsuspitsyn.quizbot.command;

import com.github.konstantinsuspitsyn.quizbot.bot.CustomQuizBot;
import com.github.konstantinsuspitsyn.quizbot.service.SendBotMessageService;
import com.github.konstantinsuspitsyn.quizbot.service.SendBotMessageServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

abstract class AbstractCommandTest {

    @Mock
    protected CustomQuizBot customQuizBot;
    protected SendBotMessageService sendBotMessageService;
    protected AutoCloseable autoCloseable;

    abstract String getCommandName();
    abstract  String getCommandMessage();
    abstract Command getCommand();

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.sendBotMessageService = new SendBotMessageServiceImpl(customQuizBot);
    }

    @SneakyThrows
    @AfterEach
    void cleanUp() {
        autoCloseable.close();
    }

    @Test
    void shouldProperlyExecuteCommand() throws TelegramApiException {
        // given
        Long chatId = 123456L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);

        // when
        getCommand().execute(update);

        // then
        Mockito.verify(customQuizBot).execute(sendMessage);

    }

}

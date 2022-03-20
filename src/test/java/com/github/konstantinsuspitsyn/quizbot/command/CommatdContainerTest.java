package com.github.konstantinsuspitsyn.quizbot.command;

import com.github.konstantinsuspitsyn.quizbot.service.AnswerService;
import com.github.konstantinsuspitsyn.quizbot.service.QuestionService;
import com.github.konstantinsuspitsyn.quizbot.service.SendBotMessageService;
import com.github.konstantinsuspitsyn.quizbot.service.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

@DisplayName("Unit-level testing for CommandContainer")
public class CommatdContainerTest {

    private CommandContainer commandContainer;
    private TelegramUserService telegramUserService;
    private QuestionService questionService;
    private AnswerService answerService;

    @BeforeEach
    public void init() {
        SendBotMessageService sendBMessageService = Mockito.mock(SendBotMessageService.class);
        commandContainer = new CommandContainer(sendBMessageService, telegramUserService, questionService, answerService);
    }

    @Test
    public void shouldGetAllTheExistingCommands() {
        //when-then
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.retrieveCommand(commandName.getCommandName());
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand() {
        //given
        String unknownCommand = "/unknowntobotcommand";

        //when
        Command command = commandContainer.retrieveCommand(unknownCommand);

        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }

}

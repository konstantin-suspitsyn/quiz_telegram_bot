package com.github.konstantinsuspitsyn.quizbot.command;

import com.github.konstantinsuspitsyn.quizbot.service.TelegramUserService;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

import static com.github.konstantinsuspitsyn.quizbot.command.CommandName.*;

@DisplayName("Test for /stop command")
public class StopCommandTest extends AbstractCommandTest {

    TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);

    @Override
    String getCommandName() {
        return STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return StopCommand.STOP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StopCommand(sendBotMessageService, telegramUserService);
    }
}

package com.github.konstantinsuspitsyn.quizbot.command;

import org.junit.jupiter.api.DisplayName;
import static com.github.konstantinsuspitsyn.quizbot.command.CommandName.*;
import static com.github.konstantinsuspitsyn.quizbot.command.HelpCommand.HELP_MESSAGE;


@DisplayName("Help Command Unit-test")
class HelpCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(sendBotMessageService);
    }
}
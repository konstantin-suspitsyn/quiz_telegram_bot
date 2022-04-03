package com.github.konstantinsuspitsyn.quizbot.command;

import com.github.konstantinsuspitsyn.quizbot.service.*;
import com.google.common.collect.ImmutableMap;
import static com.github.konstantinsuspitsyn.quizbot.command.CommandName.*;

/**
 * Container of all commands
 * If make new command, fill it in here
 */
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, QuestionService questionService, AnswerService answerService, UserRecordService userRecordService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService,telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService))
                .put(GETNAME.getCommandName(), new GetNameCommand(sendBotMessageService, telegramUserService))
                .put(COMMANDFROMSTATE.getCommandName(), new CommandFromState(telegramUserService))
                .put(ASKQUESTION.getCommandName(), new QuestionAnswerCommand(sendBotMessageService, questionService, answerService, userRecordService))
                .put(MYSTATISTICS.getCommandName(), new MyStatisticsCommand(sendBotMessageService, userRecordService))
                .put(DELETEMYRESULTS.getCommandName(), new DeleteMyResults(sendBotMessageService, userRecordService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);

    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}

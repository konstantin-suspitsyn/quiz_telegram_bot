package com.github.konstantinsuspitsyn.quizbot.command;

import com.github.konstantinsuspitsyn.quizbot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.konstantinsuspitsyn.quizbot.command.CommandName.*;

/**
 * Help Command
 */
public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("<b>Доступные команды:</b>\n" +
            "%s — начать работу с ботом\n" +
            "%s — закончить работу с ботом\n\n" +
            "%s — помощь по работе с ботом\n\n" +
            "%s — начать квиз\n" +
            "%s — просмотреть свои результаты\n" +
            "%s — удалить сови результаты\n",
            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName(), ASKQUESTION.getCommandName(), MYSTATISTICS.getCommandName(), DELETEMYRESULTS.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}

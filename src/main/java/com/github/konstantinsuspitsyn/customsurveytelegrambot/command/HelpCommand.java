package com.github.konstantinsuspitsyn.customsurveytelegrambot.command;

import com.github.konstantinsuspitsyn.customsurveytelegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.konstantinsuspitsyn.customsurveytelegrambot.command.CommandName.*;

/**
 * Help Command
 */
public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("Доступные команды:\n" +
            "%s — начать работу с ботом\n" +
            "%s — закончить работу с ботом\n" +
            "%s — помощь по работе с ботом\n",
            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}

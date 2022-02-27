package com.github.konstantinsuspitsyn.customsurveytelegrambot.command;

import com.github.konstantinsuspitsyn.customsurveytelegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * If Command is Unknown
 */
public class UnknownCommand implements Command{


    private final SendBotMessageService sendBotMessageService;

    public static final String UNKNOWN_COMMAND_MESSAGE = "Не распознанная команда\n"+
            "Напишите /help, чтобы узнать что я умею";

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_COMMAND_MESSAGE);
    }
}

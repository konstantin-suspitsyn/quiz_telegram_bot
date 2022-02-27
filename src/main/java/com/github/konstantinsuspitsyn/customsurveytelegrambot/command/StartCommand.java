package com.github.konstantinsuspitsyn.customsurveytelegrambot.command;

import com.github.konstantinsuspitsyn.customsurveytelegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start Bot Command
 */
public class StartCommand implements Command {

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE = "Привет! Бот по опросов начал работу";


    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}

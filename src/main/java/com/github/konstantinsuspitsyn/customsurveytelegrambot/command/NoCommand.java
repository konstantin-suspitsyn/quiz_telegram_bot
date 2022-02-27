package com.github.konstantinsuspitsyn.customsurveytelegrambot.command;

import com.github.konstantinsuspitsyn.customsurveytelegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * If No Command was found
 */
public class NoCommand implements Command {

    public NoCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    private final SendBotMessageService sendBotMessageService;

    public static final String NO_COMMAND_MESSAGE = "Команда не поддерживается.\n" +
            "Набери /help, чтобы посмотреть список доступных команд";

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), NO_COMMAND_MESSAGE);
    }
}

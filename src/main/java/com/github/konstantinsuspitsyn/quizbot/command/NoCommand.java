package com.github.konstantinsuspitsyn.quizbot.command;

import com.github.konstantinsuspitsyn.quizbot.service.SendBotMessageService;
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
        String chatId;

        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        } else {
            chatId = update.getMessage().getChatId().toString();
        }

        sendBotMessageService.sendMessage(chatId, NO_COMMAND_MESSAGE);
    }
}

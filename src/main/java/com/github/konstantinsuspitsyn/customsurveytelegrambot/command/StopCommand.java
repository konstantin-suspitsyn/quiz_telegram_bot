package com.github.konstantinsuspitsyn.customsurveytelegrambot.command;

import com.github.konstantinsuspitsyn.customsurveytelegrambot.service.SendBotMessageService;
import com.github.konstantinsuspitsyn.customsurveytelegrambot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Stop the Bot
 */
public class StopCommand implements Command {



    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String STOP_MESSAGE = "Бот закончил работу";

    public StopCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
        telegramUserService.findByChatID(update.getMessage().getChatId().toString()).ifPresent(it -> {
            it.setActive(false);
            telegramUserService.save(it);
        });
    }

}

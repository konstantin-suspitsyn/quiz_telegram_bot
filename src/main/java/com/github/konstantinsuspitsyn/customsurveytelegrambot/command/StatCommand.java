package com.github.konstantinsuspitsyn.customsurveytelegrambot.command;

import com.github.konstantinsuspitsyn.customsurveytelegrambot.service.SendBotMessageService;
import com.github.konstantinsuspitsyn.customsurveytelegrambot.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatCommand implements Command{


    private final TelegramUserService telegramUserService;
    private final SendBotMessageService sendBotMessageService;

    public final String STAT_MESSAGE = "Кол-во активных пользователей = %s";

    @Autowired
    public StatCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.telegramUserService = telegramUserService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        int activeUsersCount = telegramUserService.retrieveAllActiveUsers().size();
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), String.format(STAT_MESSAGE, activeUsersCount));
    }
}

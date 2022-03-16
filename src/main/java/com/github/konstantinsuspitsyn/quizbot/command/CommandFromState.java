package com.github.konstantinsuspitsyn.quizbot.command;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.TelegramUser;
import com.github.konstantinsuspitsyn.quizbot.repository.entity.TelegramUserRepository;
import com.github.konstantinsuspitsyn.quizbot.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;


public class CommandFromState implements Command{

    public CommandFromState(TelegramUserService telegramUserService) {
        this.telegramUserService = telegramUserService;
    }

    private final TelegramUserService telegramUserService;

    @Override
    public void execute(Update update) {

    }

    @Override
    public String retrieveCommand(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        TelegramUser user = telegramUserService.findByChatID(chatId).get();

        if (user == null) {
            return "";
        } else {
            return user.getState();
        }
    }

}

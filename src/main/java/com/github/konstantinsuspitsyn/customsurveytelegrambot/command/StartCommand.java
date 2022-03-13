package com.github.konstantinsuspitsyn.customsurveytelegrambot.command;

import com.github.konstantinsuspitsyn.customsurveytelegrambot.repository.entity.TelegramUser;
import com.github.konstantinsuspitsyn.customsurveytelegrambot.service.SendBotMessageService;
import com.github.konstantinsuspitsyn.customsurveytelegrambot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start Bot Command
 */
public class StartCommand implements Command {


    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    public final static String START_MESSAGE = "Привет! Бот по опросов начал работу";

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }


    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        // User ID here
        String userName = update.getMessage().getFrom().getUserName();

        if (userName == null) {
            // if user doesn't have @name in telegram

            telegramUserService.findByChatID(chatId).ifPresentOrElse(
                    user -> {
                        user.setActive(true);
                        telegramUserService.save(user);
                    },
                    () -> {
                        TelegramUser telegramUser = new TelegramUser();
                        telegramUser.setActive(true);
                        telegramUser.setChatId(chatId);
                        telegramUserService.save(telegramUser);
                    }
            );
        } else {

            telegramUserService.findByUserName(userName).ifPresentOrElse(
                    user -> {
                        user.setActive(true);
                        user.setChatId(chatId);
                        telegramUserService.save(user);
                    },
                    () -> {
                        TelegramUser telegramUser = new TelegramUser();
                        telegramUser.setActive(true);
                        telegramUser.setUserName(userName);
                        telegramUser.setChatId(chatId);
                        telegramUserService.save(telegramUser);
                    }
            );

        }


        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}

package com.github.konstantinsuspitsyn.quizbot.command;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.TelegramUser;
import com.github.konstantinsuspitsyn.quizbot.service.SendBotMessageService;
import com.github.konstantinsuspitsyn.quizbot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.konstantinsuspitsyn.quizbot.command.CommandName.*;

/**
 * Start Bot Command
 */
public class StartCommand implements Command {


    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    public final static String START_MESSAGE = "Привет! Бот по опросам начал работу";
    public final static String ASK_FOR_NAME = "Напиши свой никнэйм";
    // If nickname is not filled, set = true
    private boolean askForNick = false;

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }


    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        // User ID here
        String userName = update.getMessage().getFrom().getUserName();


        telegramUserService.findByChatID(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    if (userName != null) {
                        user.setUserName(userName);
                    }
                    if (user.getNickName() == null) {
                        askForNick = true;
                    }
                    user.setState(GETNAME.getCommandName());
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    if (userName != null) {
                        telegramUser.setUserName(userName);
                    }
                    telegramUser.setState(GETNAME.getCommandName());
                    telegramUserService.save(telegramUser);
                    askForNick = true;
                }
        );

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
        if (askForNick == true) {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), ASK_FOR_NAME);
        }

    }
}

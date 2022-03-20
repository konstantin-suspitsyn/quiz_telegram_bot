package com.github.konstantinsuspitsyn.quizbot.command;

import com.github.konstantinsuspitsyn.quizbot.service.SendBotMessageService;
import com.github.konstantinsuspitsyn.quizbot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.github.konstantinsuspitsyn.quizbot.command.CommandName.*;

/**
 * If name was not saved yet
 * We ask user for his name and wanting accept response
 */
public class GetNameCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public GetNameCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {

        String yourName = "Ваше имя зафиксировано как %s. Все точно?";

        String chatId;

        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            telegramUserService.findByChatID(chatId).ifPresentOrElse(
                    user -> {
                        user.setState(null);
                        telegramUserService.save(user);
                        sendBotMessageService.sendMessage(chatId, "Вы готовы приступать к квизу");
                    },
                    () -> {
                        sendBotMessageService.sendMessage(chatId, "Начните со " + START.getCommandName());
                    }
            );
        } else {
            chatId = update.getMessage().getChatId().toString();
            String userNickName = update.getMessage().getText();
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText("Да, все точно!");
            inlineKeyboardButton.setCallbackData(GETNAME.getCommandName() + " " + userNickName);

            List<InlineKeyboardButton> newList = new ArrayList<>();
            newList.add(inlineKeyboardButton);
            inlineKeyboardMarkup.setKeyboard(Collections.singletonList(newList));

            telegramUserService.findByChatID(chatId).ifPresentOrElse(
                    user -> {
                        user.setNickName(userNickName);
                        telegramUserService.save(user);
                    },
                    () -> {
                        sendBotMessageService.sendMessage(chatId, START.getCommandName());
                    }
            );

            sendBotMessageService.sendMessage(chatId, String.format(yourName, userNickName), inlineKeyboardMarkup);
        }

    }
}

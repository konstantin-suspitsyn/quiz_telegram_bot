package com.github.konstantinsuspitsyn.quizbot.service;

import com.github.konstantinsuspitsyn.quizbot.bot.CustomQuizBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final CustomQuizBot customQuizBot;

    @Autowired
    public SendBotMessageServiceImpl(CustomQuizBot customQuizBot) {
        this.customQuizBot = customQuizBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            customQuizBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            // TODO: Add log
            e.printStackTrace();
        }

    }

    @Override
    public void sendMessage(String chatId, String message, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            customQuizBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            // TODO: Add log
            e.printStackTrace();
        }
    }
}

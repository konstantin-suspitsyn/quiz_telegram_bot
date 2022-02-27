package com.github.konstantinsuspitsyn.customsurveytelegrambot.service;

import com.github.konstantinsuspitsyn.customsurveytelegrambot.bot.CustomServeyTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final CustomServeyTelegramBot customServeyTelegramBot;

    @Autowired
    public SendBotMessageServiceImpl(CustomServeyTelegramBot customServeyTelegramBot) {
        this.customServeyTelegramBot = customServeyTelegramBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            customServeyTelegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            // TODO: Add log
            e.printStackTrace();
        }

    }
}

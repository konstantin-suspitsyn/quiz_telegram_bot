package com.github.konstantinsuspitsyn.quizbot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Sends messages to Telegram Bot
 */
public interface SendBotMessageService {
    /**
     * Send message via telegram bot
     * @param chatId
     * @param message
     */
    void sendMessage(String chatId, String message);

    void sendMessage(String chatId, String message, InlineKeyboardMarkup inlineKeyboardMarkup);
}

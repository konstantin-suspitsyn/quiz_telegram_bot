package com.github.konstantinsuspitsyn.customsurveytelegrambot.service;

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
}

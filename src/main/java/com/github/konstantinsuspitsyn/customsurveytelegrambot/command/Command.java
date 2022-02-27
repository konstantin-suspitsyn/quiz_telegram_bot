package com.github.konstantinsuspitsyn.customsurveytelegrambot.command;


import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Command interface for handling commands to our bot
 */
public interface Command {
    void execute(Update update);
}

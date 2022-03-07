package com.github.konstantinsuspitsyn.customsurveytelegrambot.bot;

import com.github.konstantinsuspitsyn.customsurveytelegrambot.command.CommandContainer;
import com.github.konstantinsuspitsyn.customsurveytelegrambot.service.SendBotMessageServiceImpl;
import com.github.konstantinsuspitsyn.customsurveytelegrambot.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import static com.github.konstantinsuspitsyn.customsurveytelegrambot.command.CommandName.*;

/**
 *  Telegram bot to create custom surveys
 */
@Component
public class CustomServeyTelegramBot extends TelegramLongPollingBot {

    // Symbol that opens command for bot
    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    public CustomServeyTelegramBot(TelegramUserService telegramUserService) {
        commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService);
    }

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0];

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }

    }
}

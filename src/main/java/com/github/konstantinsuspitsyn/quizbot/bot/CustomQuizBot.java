package com.github.konstantinsuspitsyn.quizbot.bot;

import com.github.konstantinsuspitsyn.quizbot.command.CommandContainer;
import com.github.konstantinsuspitsyn.quizbot.service.SendBotMessageServiceImpl;
import com.github.konstantinsuspitsyn.quizbot.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import static com.github.konstantinsuspitsyn.quizbot.command.CommandName.*;

/**
 *  Telegram bot to create custom surveys
 */
@Component
public class CustomQuizBot extends TelegramLongPollingBot {

    // Symbol that opens command for bot
    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    public CustomQuizBot(TelegramUserService telegramUserService) {
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

        boolean sendAction = false;
        String message = null;
        String commandIdentifier = null;

        // Getting message if exists
        if (update.hasMessage() && update.getMessage().hasText()) {
            sendAction = true;
            message = update.getMessage().getText().trim();
        } else if (update.hasCallbackQuery()) {
            sendAction = true;
            message = update.getCallbackQuery().getData();
        }

        // Getting command from message
        if (!message.startsWith(COMMAND_PREFIX)) {
            // if no message check state of user and may be command is there
            commandIdentifier = commandContainer.retrieveCommand(COMMANDFROMSTATE.getCommandName()).retrieveCommand(update);
        } else if (message.startsWith(COMMAND_PREFIX)) {
            commandIdentifier = message.split(" ")[0];
        }

        // Run command
        if ((sendAction == true) & (commandIdentifier != null)) {
            if (commandIdentifier.startsWith(COMMAND_PREFIX)) {
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }

    }
}

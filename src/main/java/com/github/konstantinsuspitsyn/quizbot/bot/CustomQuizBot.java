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

        if (update.hasMessage() && update.getMessage().hasText()) {
            String commandIdentifier;
            String message = update.getMessage().getText().trim();

            // We are getting command from /command message or from user state
            if (message.startsWith(COMMAND_PREFIX)) {
                commandIdentifier = message.split(" ")[0];
            } else {
                String chatId = update.getMessage().getChatId().toString();
                commandIdentifier = commandContainer.retrieveCommand(COMMANDFROMSTATE.getCommandName()).retrieveCommand(update);
            }

            // Running command
            if (commandIdentifier.startsWith(COMMAND_PREFIX)) {
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }

        } else if (update.hasCallbackQuery()) {
            System.out.println(update.getCallbackQuery().getMessage());
            System.out.println(update.getCallbackQuery().getData());
            System.out.println(update.getCallbackQuery().getId());
            System.out.println(update.getCallbackQuery().getChatInstance());

            String commandIdentifier;
            String message = update.getCallbackQuery().getData();

            if (message.startsWith(COMMAND_PREFIX)) {
                commandIdentifier = message.split(" ")[0];
            } else {
                String chatId = update.getMessage().getChatId().toString();
                commandIdentifier = commandContainer.retrieveCommand(COMMANDFROMSTATE.getCommandName()).retrieveCommand(update);
            }
            // TODO: Check if not null
            if (commandIdentifier.startsWith(COMMAND_PREFIX)) {
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }


        }

    }
}

package com.github.konstantinsuspitsyn.quizbot.command;

import com.github.konstantinsuspitsyn.quizbot.service.SendBotMessageService;
import com.github.konstantinsuspitsyn.quizbot.service.UserRecordService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.konstantinsuspitsyn.quizbot.command.CommandName.ASKQUESTION;

public class DeleteMyResults implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final UserRecordService userRecordService;

    public DeleteMyResults(SendBotMessageService sendBotMessageService, UserRecordService userRecordService) {
        this.sendBotMessageService = sendBotMessageService;
        this.userRecordService = userRecordService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        userRecordService.deleteUserResults(chatId);

        sendBotMessageService.sendMessage(chatId, "Ваши результаты удалены\n" +
                "Можете начать заново " + ASKQUESTION.getCommandName());

    }
}

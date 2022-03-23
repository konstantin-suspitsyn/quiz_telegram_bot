package com.github.konstantinsuspitsyn.quizbot.command;

import com.github.konstantinsuspitsyn.quizbot.service.SendBotMessageService;
import com.github.konstantinsuspitsyn.quizbot.service.UserRecordService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyStatisticsCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final UserRecordService userRecordService;


    public MyStatisticsCommand(SendBotMessageService sendBotMessageService, UserRecordService userRecordService) {
        this.sendBotMessageService = sendBotMessageService;
        this.userRecordService = userRecordService;
    }

    @Override
    public void execute(Update update) {

        String chatId = update.getMessage().getChatId().toString();
        String result;

        int myCorrectAnswersSum = userRecordService.getSumOfCorrectAnswers(chatId);
        int myAllAnswersCount = userRecordService.getCountOfAllAnswers(chatId);

        int myScore = Math.round((float) myCorrectAnswersSum / (float) myAllAnswersCount * 100);

        result = "Твой результат составил " +
                myScore + "%.\n" +
                "Отвечено правильно на " + myCorrectAnswersSum +
                " из " + myAllAnswersCount;

        sendBotMessageService.sendMessage(chatId, result);
    }

}

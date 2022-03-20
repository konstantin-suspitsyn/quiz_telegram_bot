package com.github.konstantinsuspitsyn.quizbot.command;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.Answers;
import com.github.konstantinsuspitsyn.quizbot.repository.entity.Question;
import com.github.konstantinsuspitsyn.quizbot.service.AnswerService;
import com.github.konstantinsuspitsyn.quizbot.service.QuestionService;
import com.github.konstantinsuspitsyn.quizbot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Gives Question with answers
 * If user gave answer tell's him was it right or wrong
 * TODO: Add score
 */
public class QuestionAnswerCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    public QuestionAnswerCommand(SendBotMessageService sendBotMessageService, QuestionService questionService, AnswerService answerService) {
        this.sendBotMessageService = sendBotMessageService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @Override
    public void execute(Update update) {
        // Temp id for question
        // TODO: Autogenerate this id
        Long idQuestion = 1L;
        String chatId = null;

        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        } else if (update.hasMessage()) {
            chatId = update.getMessage().getChatId().toString();
        }

        Question question = questionService.findById(idQuestion).get();

        List<Answers> answersList = answerService.getAnswerByQuestionId(idQuestion);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> newList = new ArrayList<>();

        for (Answers answer:
             answersList) {
            newList.add(new InlineKeyboardButton());
            newList.get(newList.size() - 1).setText(answer.getAnswer());
            newList.get(newList.size() - 1).setCallbackData(answer.getId().toString());
        }

        List<List<InlineKeyboardButton>> listOfRows = new ArrayList<>();
        listOfRows.add(newList);

        inlineKeyboardMarkup.setKeyboard(listOfRows);

        sendBotMessageService.sendMessage(chatId, question.getQuestion(), inlineKeyboardMarkup);


    }
}

package com.github.konstantinsuspitsyn.quizbot.command;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.Answers;
import com.github.konstantinsuspitsyn.quizbot.repository.entity.Question;
import com.github.konstantinsuspitsyn.quizbot.repository.entity.UserRecord;
import com.github.konstantinsuspitsyn.quizbot.service.AnswerService;
import com.github.konstantinsuspitsyn.quizbot.service.QuestionService;
import com.github.konstantinsuspitsyn.quizbot.service.SendBotMessageService;
import com.github.konstantinsuspitsyn.quizbot.service.UserRecordService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.konstantinsuspitsyn.quizbot.command.CommandName.ASKQUESTION;
import static com.github.konstantinsuspitsyn.quizbot.command.CommandName.STOP;

/**
 * Gives Question with answers
 * If user gave answer tell's him was it right or wrong
 * TODO: Add score
 */
public class QuestionAnswerCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserRecordService userRecordService;

    private final String STOP_QUESTIONS = "Довольно вопросов";

    public QuestionAnswerCommand(SendBotMessageService sendBotMessageService, QuestionService questionService, AnswerService answerService, UserRecordService userRecordService) {
        this.sendBotMessageService = sendBotMessageService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.userRecordService = userRecordService;
    }

    /**
     * Generates new set of Questions for Quiz
     * @param chatId
     */
    public void sendNewSetOfQuestions(String chatId) {


        List<Question> questionsList = questionService.getNextQuestion(chatId);

        if (questionsList.isEmpty()) {
            sendBotMessageService.sendMessage(chatId, "Вопросы закончились");
            return;
        }

        Question question = questionsList.get(0);
        List<Answers> answersList = answerService.getAnswerByQuestionId(question.getId());

        // Creating answers buttons block
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> answersButtonList = new ArrayList<>();
        for (Answers answer:
                answersList) {
            answersButtonList.add(new InlineKeyboardButton());
            answersButtonList.get(answersButtonList.size() - 1).setText(answer.getAnswer());

            // Set call back data in format: /command question_id:1 answer:1
            answersButtonList.get(answersButtonList.size() - 1).setCallbackData(
                    ASKQUESTION.getCommandName() + " "
                    + "question_id:" + question.getId() + " "
                    + "answer:" + answer.getId().toString());
        }

        // Stop quiz block
        List<InlineKeyboardButton> stopQuestions = new ArrayList<>();
        InlineKeyboardButton stopButton = new InlineKeyboardButton();
        stopButton.setText(STOP_QUESTIONS);
        stopButton.setCallbackData(STOP.getCommandName());
        stopQuestions.add(stopButton);

        List<List<InlineKeyboardButton>> listOfButtonRows = new ArrayList<>();
        listOfButtonRows.add(answersButtonList);
        listOfButtonRows.add(stopQuestions);

        inlineKeyboardMarkup.setKeyboard(listOfButtonRows);

        sendBotMessageService.sendMessage(chatId, question.getQuestion(), inlineKeyboardMarkup);
    }

    /**
     * Get map with answers to process
     * @param toSeparate Get call back data in format: /command question_id:1 answer:1
     * @return HashMap {question_id:1, answer:1}
     */
    public Map<String, Integer> getAnswers(String toSeparate) {
        Map<String, Integer> answers  = new HashMap<String, Integer>();
        String[] separated = toSeparate.split(" ");
        for (int i = 1; i<separated.length; i++) {
            answers.put(separated[i].split(":")[0], Integer.valueOf(separated[i].split(":")[1]));
        }
        return answers;
    }

    /**
     * Designed to work with CallbackQuery ONLY
     * Checks answer from question
     */
    public void checkAnswer(String message, String chatId) {
        Map<String, Integer> answersMap = getAnswers(message);

        Integer answerId = answersMap.get("answer");
        Long questionId = Long.valueOf(answersMap.get("question_id"));

        Answers answer = answerService.findById(Long.valueOf(answerId)).get();

        UserRecord userRecord = new UserRecord();
        userRecord.setUserId(chatId);
        userRecord.setQuestionId(questionId);

        String messageText;

        if (answer.getCorrect() == 1) {
            messageText = "Это правильный ответ";
            userRecord.setCorrect(1);

        } else {
            messageText = "Это неправильный ответ";
            userRecord.setCorrect(0);
        }
        userRecordService.save(userRecord);
        sendBotMessageService.sendMessage(chatId, messageText);

    }

    @Override
    public void execute(Update update) {
        String chatId = null;
        String message = null;

        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            message = update.getCallbackQuery().getData();
            checkAnswer(message, chatId);
        } else if (update.hasMessage()) {
            chatId = update.getMessage().getChatId().toString();
        }

        sendNewSetOfQuestions(chatId);

    }
}

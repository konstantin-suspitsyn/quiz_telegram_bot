package com.github.konstantinsuspitsyn.quizbot.service;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.UserRecord;

public interface UserRecordService {

    void save(UserRecord userRecord);

    int getSumOfCorrectAnswers(String chatId);

    int getCountOfAllAnswers(String chatId);

    void deleteUserResults(String chatId);

}

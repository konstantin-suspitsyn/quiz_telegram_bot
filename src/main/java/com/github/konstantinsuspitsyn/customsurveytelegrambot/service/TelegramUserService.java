package com.github.konstantinsuspitsyn.customsurveytelegrambot.service;

import com.github.konstantinsuspitsyn.customsurveytelegrambot.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

public interface TelegramUserService {

    void save(TelegramUser telegramUser);

    List<TelegramUser> retrieveAllActiveUsers();

    Optional<TelegramUser> findByChatID(String chatId);

}

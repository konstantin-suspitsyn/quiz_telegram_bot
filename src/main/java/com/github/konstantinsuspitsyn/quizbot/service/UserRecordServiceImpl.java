package com.github.konstantinsuspitsyn.quizbot.service;

import com.github.konstantinsuspitsyn.quizbot.repository.entity.UserRecord;
import com.github.konstantinsuspitsyn.quizbot.repository.entity.UserRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRecordServiceImpl implements UserRecordService {

    private UserRecordRepository userRecordRepository;

    @Autowired
    public UserRecordServiceImpl(UserRecordRepository userRecordRepository) {
        this.userRecordRepository = userRecordRepository;
    }

    @Override
    public void save(UserRecord userRecord) {
        userRecordRepository.save(userRecord);
    }
}

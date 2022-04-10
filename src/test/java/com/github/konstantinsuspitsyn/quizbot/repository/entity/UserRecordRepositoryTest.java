package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test for UserRecordRepository")
@DataJpaTest
class UserRecordRepositoryTest {

    public static final String TEST_USER_ID = "123";
    public static final long QUESTION_ID_TRUE = 1L;
    public static final long QUESTION_ID_FALSE = 2L;

    @Autowired
    private UserRecordRepository userRecordRepository;
    @Autowired
    private QuestionRepository questionRepository;


    @BeforeEach
    void setUp() {
        // given

        Question questionTrue = new Question();
        questionTrue.setId(QUESTION_ID_TRUE);
        questionRepository.save(questionTrue);

        UserRecord userRecordTrue = new UserRecord();
        userRecordTrue.setUserId(TEST_USER_ID);
        userRecordTrue.setQuestionId(QUESTION_ID_TRUE);
        userRecordTrue.setCorrect(1);
        userRecordRepository.save(userRecordTrue);

        Question questionFalse = new Question();
        questionFalse.setId(QUESTION_ID_FALSE);
        questionRepository.save(questionFalse);

        UserRecord userRecordFalse = new UserRecord();
        userRecordFalse.setUserId(TEST_USER_ID);
        userRecordFalse.setQuestionId(QUESTION_ID_FALSE);
        userRecordFalse.setCorrect(0);
        userRecordRepository.save(userRecordFalse);
    }

    @AfterEach
    void Clean() {
        questionRepository.deleteAll();
        userRecordRepository.deleteAll();
    }

    @Test
    void shouldGiveSumOfCorrectAnswers() {
        // when
        int sumOfCorrectAnswers = userRecordRepository.getSumOfCorrectAnswers(TEST_USER_ID);
        // then
        assertTrue(sumOfCorrectAnswers == 1);
    }

    @Test
    void shouldGiveCountOfAllAnswers() {
        // when
        int countOfAllAnswers = userRecordRepository.getCountOfAllAnswers(TEST_USER_ID);
        // then
        assertTrue(countOfAllAnswers == 2);
    }

    @Test
    void shouldDeleteUserResults() {
        // when
        userRecordRepository.deleteUserResults(TEST_USER_ID);
        int sumOfCorrectAnswers = userRecordRepository.getCountOfAllAnswers(TEST_USER_ID);
        // then
        assertTrue(sumOfCorrectAnswers == 0);
    }
}
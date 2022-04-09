package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@DataJpaTest
class AnswersRepositoryTest {

    public static final String TO_BE_OR_NOT_TO_BE = "To be or not to be?";
    public static final long QUESTION_ID = 1L;
    public static final long ANSWER_ID_1 = 1L;
    public static final String WHO_LET_THE_DOGS = "Who let the dogs?";
    public static final long ANSWER_ID_2 = 2L;
    @Autowired
    private AnswersRepository answersRepository;

    @BeforeEach
    void setUp() {
        // given
        Answers answers = new Answers();
        answers.setId(ANSWER_ID_1);
        answers.setQuestionId(QUESTION_ID);
        answers.setAnswer(TO_BE_OR_NOT_TO_BE);
        answers.setCorrect(1);
        answersRepository.save(answers);

        // given
        Answers answers_2 = new Answers();
        answers_2.setId(ANSWER_ID_2);
        answers_2.setQuestionId(QUESTION_ID);
        answers_2.setAnswer(WHO_LET_THE_DOGS);
        answers_2.setCorrect(0);
        answersRepository.save(answers_2);
    }

    @AfterEach
    void cleanUp() {
        answersRepository.deleteAll();
    }

    @Test
    void shouldGetByQuestionById() {
        // when
        List<Answers> getAnswersByQuestionId = answersRepository.getByQuestionId(QUESTION_ID);

        // then
        assertTrue(getAnswersByQuestionId.size() == 2);
    }

    @Test
    void shouldGetById() {
        // when
        Answers getAnswerById = answersRepository.getById(ANSWER_ID_2);

        // then
        assertThat(getAnswerById.getAnswer()).isEqualTo(WHO_LET_THE_DOGS);
    }

}
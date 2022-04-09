package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DisplayName("Test for QuestionRepository")
class QuestionRepositoryTest {

    public static final long ID_1 = 1L;
    public static final String WHERE_IS_MY_CAR_DUDE = "Where is my car, dude";
    public static final long ID_2 = 2L;
    public static final String WHO_IS_YOUR_FATHER_LUKE = "Who is your father, Luke?";
    private static final String TEST_USER_ID = "TID";
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRecordRepository userRecordRepository;

    @BeforeEach
    void setUp() {
        // given
        Question question = new Question();
        question.setId(ID_1);
        question.setQuestion(WHERE_IS_MY_CAR_DUDE);
        questionRepository.save(question);

        Question questionNext = new Question();
        questionNext.setId(ID_2);
        questionNext.setQuestion(WHO_IS_YOUR_FATHER_LUKE);
        questionRepository.save(questionNext);

    }

    @AfterEach
    void cleanUp() {

        questionRepository.deleteAll();

    }

    @Test
    void shouldGetById() {
        //when
        Question questionGet = questionRepository.getById(ID_1);

        // then
        assertThat(questionGet.getQuestion()).isEqualTo(WHERE_IS_MY_CAR_DUDE);

    }

    @Test
    void shouldGetNextQuestion() {
        // given
        Question questionTrue = new Question();
        questionTrue.setId(ID_1);
        questionRepository.save(questionTrue);

        UserRecord userRecordTrue = new UserRecord();
        userRecordTrue.setUserId(TEST_USER_ID);
        userRecordTrue.setQuestionId(ID_1);
        userRecordTrue.setCorrect(1);
        userRecordRepository.save(userRecordTrue);

        // when
        List<Question> getNextQ = questionRepository.getNextQuestion(TEST_USER_ID);
        Question nextQ = getNextQ.get(0);

        // then
        assertThat(nextQ.getQuestion()).isEqualTo(WHO_IS_YOUR_FATHER_LUKE);
    }
}
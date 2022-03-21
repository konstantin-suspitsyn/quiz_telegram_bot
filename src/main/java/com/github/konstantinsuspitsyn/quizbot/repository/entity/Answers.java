package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity(name = "answers")
@Table(name = "answers")
public class Answers {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "answer")
    private String answer;

    @Column(name = "correct")
    private int correct;
}

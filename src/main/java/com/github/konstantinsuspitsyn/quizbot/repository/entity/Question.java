package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "question")
@Entity
public class Question {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "question")
    private String question;

}

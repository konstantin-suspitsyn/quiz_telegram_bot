package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "UserRecord")
@Table(name = "user_record")
@Data
public class UserRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "correct")
    private int correct;
}

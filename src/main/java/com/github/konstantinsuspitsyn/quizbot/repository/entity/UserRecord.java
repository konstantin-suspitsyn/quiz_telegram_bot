package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_record")
@Data
public class UserRecord {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "correct")
    private boolean correct;
}

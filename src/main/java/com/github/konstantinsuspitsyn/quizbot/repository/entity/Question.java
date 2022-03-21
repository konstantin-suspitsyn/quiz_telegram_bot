package com.github.konstantinsuspitsyn.quizbot.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "question")
@Entity
public class Question {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "question")
    private String question;

    @OneToMany(targetEntity = UserRecord.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private List<UserRecord> userRecords;

}

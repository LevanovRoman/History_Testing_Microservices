package com.myapp.historytestmicroservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 450)
    private String question_title;

    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String right_answer;
    private String difficulty_level;
    private String category;

    public Question(String questionTitle, String option1, String option2, String option3, String option4, String rightAnswer, String difficultyLevel, String category) {
    }

    public Question() {

    }
}

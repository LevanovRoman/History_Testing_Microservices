package com.myapp.fronthistorytesting.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizDto {
    String categoryName;
    Integer numQuestions;
    String title;
}

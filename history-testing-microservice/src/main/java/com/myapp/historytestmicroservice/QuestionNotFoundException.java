package com.myapp.historytestmicroservice;

public class QuestionNotFoundException extends Throwable {
    public QuestionNotFoundException(String message) {
        super(message);
    }
}

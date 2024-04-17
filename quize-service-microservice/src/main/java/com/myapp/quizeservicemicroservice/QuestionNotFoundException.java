package com.myapp.quizeservicemicroservice;

public class QuestionNotFoundException extends Throwable {
    public QuestionNotFoundException(String message) {
        super(message);
    }
}

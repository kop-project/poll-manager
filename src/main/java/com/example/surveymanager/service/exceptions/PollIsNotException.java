package com.example.surveymanager.service.exceptions;

public class PollIsNotException extends NullPointerException {
    public PollIsNotException(String msg) {
        super(msg);
    }
}
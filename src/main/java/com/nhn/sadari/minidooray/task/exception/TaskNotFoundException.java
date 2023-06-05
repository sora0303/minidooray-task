package com.nhn.sadari.minidooray.task.exception;

public class TaskNotFoundException extends RuntimeException {
    private static final String MESSAGE="업무가 존재하지 않습니다. taskId : ";
    public TaskNotFoundException(long taskId){
        super(MESSAGE + taskId);
    }
}
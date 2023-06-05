package com.nhn.sadari.minidooray.task.exception;

public class TagNotFoundException extends RuntimeException {
    private static final String MESSAGE="태그가 존재하지 않습니다. tagId : ";
    public TagNotFoundException(long tagId){
        super(MESSAGE + tagId);
    }
}
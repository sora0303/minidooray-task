package com.nhn.sadari.minidooray.task.exception;

public class CommentNotFoundException extends RuntimeException {
    private static final String MESSAGE="코멘트가 존재하지 않습니다. commentId : ";
    public CommentNotFoundException(long commentId){
        super(MESSAGE + commentId);
    }
}
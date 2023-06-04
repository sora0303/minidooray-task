package com.nhn.sadari.minidooray.task.exception;

public class ProjectNotFoundException extends RuntimeException {
    private static final String MESSAGE="프로젝트가 존재하지 않습니다. projectId : ";
    public ProjectNotFoundException(long projectId){
        super(MESSAGE + projectId);
    }
}
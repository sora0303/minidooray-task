package com.nhn.sadari.minidooray.task.exception;

public class ProjectStatusNotFoundException extends RuntimeException {
    private static final String MESSAGE="프로젝트 상태가 존재하지 않습니다. projectStatus : ";
    public ProjectStatusNotFoundException(String projectStatus){
        super(MESSAGE + projectStatus);
    }
}
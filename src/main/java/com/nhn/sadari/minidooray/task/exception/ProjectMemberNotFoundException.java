package com.nhn.sadari.minidooray.task.exception;

public class ProjectMemberNotFoundException extends RuntimeException {
    private static final String MESSAGE="번 프로젝트에 해당 멤버가 존재하지 않습니다. memberId : ";
    public ProjectMemberNotFoundException(Long projectId, Long memberId){
        super(projectId + MESSAGE + memberId);
    }
}
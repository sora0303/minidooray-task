package com.nhn.sadari.minidooray.task.exception;

public class MilestoneNotFoundException extends RuntimeException {
    private static final String MESSAGE="마일스톤이 존재하지 않습니다. milestoneId : ";
    public MilestoneNotFoundException(long milestoneId){
        super(MESSAGE + milestoneId);
    }
}
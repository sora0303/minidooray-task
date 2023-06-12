package com.nhn.sadari.minidooray.task.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonResponse<T> {
    private Header header;
    private List<T> result;
    private int totalCount;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Header {
        private boolean isSuccessful;
        private int resultCode;
        private String resultMessage;
    }

    public int getTotalCount() {
        return result == null? 0 : result.size();
    }
}
package com.nhn.sadari.minidooray.task.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagCreateRequest {

    @NotNull
    private String name;
}

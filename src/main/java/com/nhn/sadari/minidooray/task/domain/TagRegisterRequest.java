package com.nhn.sadari.minidooray.task.domain;

import com.sun.istack.NotNull;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagRegisterRequest {

    @NotNull
    @NotBlank
    private String name;
}

package me.study.resttemplate.error.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InternalServerErrorVO {

    String timestamp;
    int status;
    String error;
    String path;
}

package com.wql.project.two.yc;

import lombok.Data;

@Data
public class ExceptionResponse {

    private Integer code = 40000;

    private String msg;

    public ExceptionResponse(String msg) {
        this.msg = msg;
    }

    public ExceptionResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

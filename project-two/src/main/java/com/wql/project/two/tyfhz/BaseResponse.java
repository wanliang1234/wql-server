package com.wql.project.two.tyfhz;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<D> implements Serializable {

    @Serial
    private static final long serialVersionUID = -2145852753254617071L;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 异常/成功 信息
     */
    private String message;

    /**
     * 200表示正常,300接口返回false，400调用异常
     */
    private int code;

    /**
     * 返回数据
     */
    private D data;

    public BaseResponse() {
        this.code = 200;
        this.message = "操作成功";
        this.success = true;
    }


    public BaseResponse(D data) {
        this();
        this.data = data;
    }

    public BaseResponse(boolean success) {
        this();
        this.success = success;
        if (!success) {
            this.message = "操作失败";
            this.code = 30000;
        }
    }

    public BaseResponse(boolean success, String msg) {
        this(success);
        this.message = msg;
    }

    public BaseResponse(int code, String msg) {
        this(false, msg);
        this.code = code;
    }

}

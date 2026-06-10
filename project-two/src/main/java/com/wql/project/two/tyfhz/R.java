package com.wql.project.two.tyfhz;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class R {

    private Integer code;    // 响应码
    private boolean success; // 成功标识
    private String message;  // 响应消息
    private Object data;     // 响应数据
    private Object token;    // token

    public static R ok() {
        R r = new R();
        r.setCode(200);
        r.setSuccess(true);
        r.setMessage("请求成功");
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(-1);
        r.setSuccess(false);
        r.setMessage("请求失败");
        return r;
    }

    public R data(Object data) {
        this.data = data;
        return this;
    }

    public R token(Object token) {
        this.token = token;
        return this;
    }
}

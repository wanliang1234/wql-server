package com.wql.project.two.query;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UserLoginQuery {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码（生产环境存储加密后的密文）
     */
    private String password;
}

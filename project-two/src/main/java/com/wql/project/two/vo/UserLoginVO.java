package com.wql.project.two.vo;

import cn.dev33.satoken.stp.SaTokenInfo;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginVO implements Serializable
{
    private SaTokenInfo token;
}

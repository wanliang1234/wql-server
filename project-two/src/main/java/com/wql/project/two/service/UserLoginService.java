package com.wql.project.two.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wql.project.two.entity.SysUser;
import com.wql.project.two.query.UserLoginQuery;
import com.wql.project.two.vo.UserLoginVO;

public interface UserLoginService extends IService<SysUser> {

    /**
     * 登录接口
     *
     * @param query
     * @return
     */
    UserLoginVO jwtToken(UserLoginQuery query);
}

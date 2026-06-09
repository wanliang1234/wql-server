package com.wql.project.two.service.impl;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wql.project.two.entity.SysUser;
import com.wql.project.two.mapper.UserLoginMapper;
import com.wql.project.two.query.UserLoginQuery;
import com.wql.project.two.service.UserLoginService;
import com.wql.project.two.vo.UserLoginVO;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, SysUser> implements UserLoginService {

    @Override
    public UserLoginVO jwtToken(UserLoginQuery query) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(SysUser::getUsername, query.getUsername())
                .eq(SysUser::getPassword, query.getPassword());
        SysUser userInfo = super.getOne(queryWrapper);
        if (userInfo != null) {
            SaLoginModel saLoginmodel = SaLoginConfig
                    .setExtra("id", userInfo.getId())
                    .setExtra("userName", userInfo.getUsername())
                    .setExtra("createTime", userInfo.getCreateTime());
            StpUtil.login(userInfo.getId(), saLoginmodel);
            UserLoginVO userLoginVO = new UserLoginVO();
            userLoginVO.setToken(StpUtil.getTokenInfo());
            return userLoginVO;
        } else {
            return null;
        }
    }
}

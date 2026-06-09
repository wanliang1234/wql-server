package com.wql.project.two.controller;

import com.wql.project.two.query.UserLoginQuery;
import com.wql.project.two.service.UserLoginService;
import com.wql.project.two.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserLoginConfig {
    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/jwtToken")
    public UserLoginVO jwtToken(@RequestBody UserLoginQuery query) {
        return userLoginService.jwtToken(query);
    }
}

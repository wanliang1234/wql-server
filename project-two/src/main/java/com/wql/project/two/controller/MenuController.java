package com.wql.project.two.controller;

import com.wql.project.two.service.MainMenuService;
import com.wql.project.two.vo.MenuTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MainMenuService menuService;

    @GetMapping("/tree")
    public List<MenuTreeVO> getMenuTree() {
        return menuService.getMenuTree();
    }
}

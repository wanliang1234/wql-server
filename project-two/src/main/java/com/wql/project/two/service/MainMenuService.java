package com.wql.project.two.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wql.project.two.entity.MainMenu;
import com.wql.project.two.vo.MenuTreeVO;

import java.util.List;

public interface MainMenuService extends IService<MainMenu> {
    List<MenuTreeVO> getMenuTree();
}

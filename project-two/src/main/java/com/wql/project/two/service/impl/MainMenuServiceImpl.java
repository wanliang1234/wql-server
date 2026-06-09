package com.wql.project.two.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wql.project.two.entity.MainMenu;
import com.wql.project.two.entity.SubMenu;
import com.wql.project.two.mapper.MainMenuMapper;
import com.wql.project.two.mapper.SubMenuMapper;
import com.wql.project.two.service.MainMenuService;
import com.wql.project.two.vo.MenuTreeVO;
import com.wql.project.two.vo.SubMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MainMenuServiceImpl extends ServiceImpl<MainMenuMapper, MainMenu> implements MainMenuService {

    @Autowired
    private MainMenuMapper mainMenuMapper;

    @Autowired
    private SubMenuMapper subMenuMapper;

    @Override
    public List<MenuTreeVO> getMenuTree() {
        // 1. 查询所有未删除的主菜单，按 submenu_name 排序（可根据需要调整）
        LambdaQueryWrapper<MainMenu> mainWrapper = new LambdaQueryWrapper<>();
        mainWrapper.eq(MainMenu::getLogicDel, "0")
                .orderByAsc(MainMenu::getSubmenuName);
        List<MainMenu> mainMenus = mainMenuMapper.selectList(mainWrapper);

        if (mainMenus.isEmpty()) {
            return List.of();
        }

        // 2. 查询所有未删除的子菜单，按 order_index 排序
        LambdaQueryWrapper<SubMenu> subWrapper = new LambdaQueryWrapper<>();
        subWrapper.eq(SubMenu::getLogicDel, "0")
                .orderByAsc(SubMenu::getOrderIndex);
        List<SubMenu> allSubMenus = subMenuMapper.selectList(subWrapper);

        // 3. 按 mainMenuId 分组
        Map<String, List<SubMenu>> subMenuGroup = allSubMenus.stream()
                .collect(Collectors.groupingBy(SubMenu::getMainMenuId));

        // 4. 组装响应 VO
        return mainMenus.stream().map(main -> {
            MenuTreeVO vo = new MenuTreeVO();
            vo.setSubmenuName(main.getSubmenuName());
            vo.setSubmenuIconType(main.getSubmenuIconType());
            vo.setSubmenuContentName(main.getSubmenuContentName());

            List<SubMenu> subs = subMenuGroup.getOrDefault(main.getId(), List.of());
            List<SubMenuVO> subVOs = subs.stream().map(sub -> {
                SubMenuVO subVO = new SubMenuVO();
                subVO.setTo(sub.getToPath());
                subVO.setMenuItemName(sub.getMenuItemName());
                subVO.setMenuItemContentName(sub.getMenuItemContentName());
                return subVO;
            }).collect(Collectors.toList());

            vo.setRouterLink(subVOs);
            return vo;
        }).collect(Collectors.toList());
    }
}

package com.wql.project.two.vo;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTreeVO {
    private String SubmenuName;          // 一级菜单唯一标识
    private String SubmenuIconType;      // 一级菜单图标
    private String SubmenuContentName;   // 一级菜单名称
    private List<SubMenuVO> routerLink;  // 二级菜单列表
}

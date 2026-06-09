package com.wql.project.two.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubMenuVO {
    private String to;                   // 跳转路由
    private String MenuItemName;         // 二级菜单唯一标识
    private String MenuItemContentName;  // 二级菜单名称
}

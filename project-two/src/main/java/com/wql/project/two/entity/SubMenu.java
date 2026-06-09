package com.wql.project.two.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sub_menu")
public class SubMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 所属主菜单ID
     */
    @TableField(value = "main_menu_id")
    private String mainMenuId;

    /**
     * 点击跳转路由
     */
    @TableField(value = "to_path")
    private String toPath;

    /**
     * 二级菜单唯一标识
     */
    @TableField(value = "menu_item_name")
    private String menuItemName;

    /**
     * 二级菜单显示名称
     */
    @TableField(value = "menu_item_content_name")
    private String menuItemContentName;

    /**
     * 排序序号
     */
    @TableField(value = "order_index")
    private Integer orderIndex;

    /**
     * [是否删除]0:未删除;1:已删除
     */
    @TableField(value = "logic_del")
    @TableLogic
    private String logicDel;

    /**
     * 创建人ID
     */
    @TableField(value = "create_id")
    private String createId;

    /**
     * 创建人名称
     */
    @TableField(value = "create_name")
    private String createName;

    /**
     * 创建时间(YYYY-MM-dd HH:mm:ss)
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private String createTime;

    /**
     * 修改人ID
     */
    @TableField(value = "update_id")
    private String updateId;

    /**
     * 修改人名称
     */
    @TableField(value = "update_name")
    private String updateName;

    /**
     * 修改时间(YYYY-MM-dd HH:mm:ss)
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
}

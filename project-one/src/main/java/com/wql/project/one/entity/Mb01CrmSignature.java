package com.wql.project.one.entity;

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
@TableName("mb01_crm_signature")
public class Mb01CrmSignature implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 签名编号
     */
    @TableId(value = "mb01_signature_id", type = IdType.NONE)
    @TableField(value = "签名编号")
    private String mb01SignatureId;
    /**
     * 账号id
     */
    @TableField(value = "账号id")
    private String mb01UserId;
    /**
     * 签名图片bt64
     */
    @TableField(value = "签名图片bt64")
    private String mb01SignatureBt;
    /**
     * [是否删除]0:未删除;1:已删除
     */
    @TableField(value = "[是否删除]0:未删除;1:已删除")
    @TableLogic
    private String logicDel;
    /**
     * 创建人ID
     */
    @TableField(value = "创建人ID")
    private String createId;
    /**
     * 创建人名称
     */
    @TableField(value = "创建人名称")
    private String createName;
    /**
     * 创建时间
     */
    @TableField(value = "创建时间")
    private String createTime;
    /**
     * 修改人ID
     */
    @TableField(value = "修改人ID")
    private String updateId;
    /**
     * 修改人名称
     */
    @TableField(value = "修改人名称")
    private String updateName;
    /**
     * 修改时间
     */
    @TableField(value = "修改时间")
    private String updateTime;
}

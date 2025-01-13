package com.alinesno.infra.smart.nlp.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文档类型
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("document_type")
public class DocumentTypeEntity extends InfraBaseEntity {

    /**
     * 类型名称
     */
    @ColumnType(length = 32)
    @ColumnComment("类型名称")
    @TableField
    private String typeName;

    /**
     * 类型描述
     */
    @ColumnType(length = 256)
    @ColumnComment("类型名称")
    @TableField
    private String typeDesc;

    /**
     * 图标
     */
    @ColumnType(length = 32)
    @ColumnComment("图标")
    @TableField
    private String icon;

    /**
     * 是否打开
     */
    @ColumnType(length = 1)
    @ColumnComment("是否打开")
    @TableField
    private Boolean isOpen;

    /**
     * 请求次数
     */
    @ColumnType(length = 11)
    @ColumnComment("请求次数")
    @TableField
    private Integer requestCount;

    /**
     * 是否限流
     */
    @ColumnType(length = 1)
    @ColumnComment("是否限流")
    @TableField
    private Boolean isRateLimited;
}

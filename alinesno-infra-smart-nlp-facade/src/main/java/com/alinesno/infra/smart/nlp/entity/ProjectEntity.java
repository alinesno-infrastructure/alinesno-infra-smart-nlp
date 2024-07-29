package com.alinesno.infra.smart.nlp.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 存储项目实体类
 * </p>
 * <p>
 * 该类继承自InfraBaseEntity，表示存储项目的基本信息。
 * </p>
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("project")
@Data
public class ProjectEntity extends InfraBaseEntity {


    /**
     * 图标
     */
    @TableField("icon")
    @ColumnType(length=32)
    @ColumnComment("图标")
    private String icon;

    /**
     * 项目名称
     */
    @TableField("project_name")
	@ColumnType(length=32)
	@ColumnComment("项目名称")
    private String projectName;

    /**
     * 项目描述
     */
    @TableField("project_desc")
    @ColumnType(length=256)
    @ColumnComment("项目描述")
    private String projectDesc;

    /**
     * 项目代码
     */
    @TableField("project_code")
	@ColumnType(length=50)
	@ColumnComment("项目代码")
    private String projectCode;

    /**
     * 开通服务  openservice  documentType
     */
    @TableField("open_service")
	@ColumnType(length=255)
	@ColumnComment("开通服务")
    private String openService ;


}

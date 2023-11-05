package com.alinesno.infra.smart.nlp.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 模型管理
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("model_management")
@Data
public class ModelManagementEntity extends InfraBaseEntity {

    /**
     * 模型的名称或标识
     */
    @TableField("model_name")
	@ColumnType(length=255)
	@ColumnComment("模型的名称或标识")
    private String modelName;

    /**
     * 存储模型文件的路径或URL
     */
    @TableField("model_path")
	@ColumnType(length=255)
	@ColumnComment("存储模型文件的路径或URL")
    private String modelPath;

    /**
     * 模型的版本号或标识
     */
    @TableField("model_version")
	@ColumnType(length=20)
	@ColumnComment("模型的版本号或标识")
    private String modelVersion;
}

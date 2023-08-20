package com.alinesno.infra.smart.nlp.entity;

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
public class ModelManagementEntity extends InfraBaseEntity {

    /**
     * 模型的名称或标识
     */
    @TableField("model_name")
    private String modelName;

    /**
     * 存储模型文件的路径或URL
     */
    @TableField("model_path")
    private String modelPath;

    /**
     * 模型的版本号或标识
     */
    @TableField("model_version")
    private String modelVersion;

    // 省略 getter 和 setter 方法


    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }
}
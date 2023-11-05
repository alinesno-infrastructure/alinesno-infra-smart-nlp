package com.alinesno.infra.smart.nlp.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 应用表
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("application")
@Data
public class ApplicationEntity extends InfraBaseEntity {

    /**
     * 应用名称
     */
    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("应用名称")
    private String name;

    /**
     * 接口密钥
     */
    @TableField("api_key")
	@ColumnType(length=255)
	@ColumnComment("接口密钥")
    private String apiKey;

    /**
     * 应用logo标识
     */
    @TableField("banner")
	@ColumnType(length=255)
	@ColumnComment("应用logo标识")
    private String banner;

    /**
     * 应用码
     */
    @TableField("app_code")
	@ColumnType(length=50)
	@ColumnComment("应用码")
    private String appCode;
}

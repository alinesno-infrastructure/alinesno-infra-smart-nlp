package com.alinesno.infra.smart.nlp.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;


/**
 * <p>
 *
 * </p>
 *
 * @author LuoXiaoDong
 * @version 1.0.0
 */
@Data
@TableName("model_management")
public class ModelManagementEntity extends InfraBaseEntity {
	/**
	 * 应用ID
	 */
	@ColumnType(MySqlTypeConstant.BIGINT)
	@ColumnComment("应用ID")
	@TableField("app_id")
	private Long appId;

	/**
	 * 模型名称
	 */
	@ColumnType(length = 100)
	@ColumnComment("模型名称")
	@TableField("model_name")
	private String modelName;

	/**
	 * 模型描述
	 */
	@ColumnType(length = 200)
	@ColumnComment("模型描述")
	@TableField("model_description")
	private String modelDescription;

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


	/**
	 * 模型状态
	 */
	@ColumnType(length = 20)
	@ColumnComment("模型状态")
	@TableField("model_status")
	private String modelStatus;

	/**
	 * 创建时间
	 */
	@ColumnType(value = MySqlTypeConstant.DATETIME, length = 18)
	@ColumnComment("创建时间")
	@TableField("create_time")
	private Date createTime;

	/**
	 * 删除标识：0-未删除，1-已删除
	 */
	@ColumnType(length = 1)
	@ColumnComment("删除标识：0-未删除，1-已删除")
	@TableField("is_deleted")
	private int isDeleted;


}

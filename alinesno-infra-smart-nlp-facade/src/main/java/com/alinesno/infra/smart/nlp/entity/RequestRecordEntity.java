package com.alinesno.infra.smart.nlp.entity;

import java.util.Date;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;


/**
 * <p>
 *
 * </p>
 *
 * @author LuoXiaoDong
 * @version 1.0.0
 */
@Data
@TableName("request_record")
public class RequestRecordEntity extends InfraBaseEntity {
	/**
	 * 应用ID
	 */
	@ColumnType(MySqlTypeConstant.BIGINT)
	@ColumnComment("应用ID")
	@TableField("app_id")
	private Long appId;

	/**
	 * 请求时间
	 */
	@ColumnType(value = MySqlTypeConstant.DATETIME, length = 18)
	@ColumnComment("请求时间")
	@TableField("request_time")
	private Date requestTime;

	/**
	 * 请求内容
	 */
	@ColumnType(MySqlTypeConstant.TEXT)
	@ColumnComment("请求内容")
	@TableField("request_content")
	private String requestContent;

	/**
	 * 响应内容
	 */
	@ColumnType(MySqlTypeConstant.TEXT)
	@ColumnComment("响应内容")
	@TableField("response_content")
	private String responseContent;

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

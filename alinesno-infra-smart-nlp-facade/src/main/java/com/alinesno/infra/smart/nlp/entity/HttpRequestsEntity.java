package com.alinesno.infra.smart.nlp.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;

/**
 * <p>
 * 请求请求记录实体类
 * </p>
 * <p>
 * 该实体类用于请求请求记录的相关信息，包括请求路径、保存路径、请求应用、请求信息、文件名称、请求IP、响应时间、请求用户、文件类型和请求用户名等。
 * </p>
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Data
@TableName("http_requests")
public class HttpRequestsEntity extends InfraBaseEntity {

    /**
     * 请求路径
     */
    @ColumnType(length=255)
    @ColumnComment("请求路径")
    @TableField("request_url")
    private String requestUrl;

    /**
     * 请求应用
     */
    @ColumnType(length=255)
    @ColumnComment("请求应用")
    @TableField("request_application")
    private String requestApplication;

    /**
     * 请求信息
     */
    @ColumnType(length=255)
    @ColumnComment("请求信息")
    @TableField("agent")
    private String agent;

    /**
     * 请求IP
     */
    @ColumnType(length=255)
    @ColumnComment("请求IP")
    @TableField("request_ip")
    private String requestIp;

    /**
     * 响应时间
     */
    @ColumnType(length=255)
    @ColumnComment("响应时间")
    @TableField("response_time")
    private long responseTime;

    /**
     * 请求用户
     */
    @ColumnType(length=255)
    @ColumnComment("请求用户")
    @TableField("request_user_id")
    private String requestUserId;

    /**
     * 文件类型
     */
    @ColumnType(length=255)
    @ColumnComment("文件类型")
    @TableField("request_type")
    private String requestType;

    /**
     * 请求用户名
     */
    @ColumnType(length=255)
    @ColumnComment("请求用户名")
    @TableField("request_username")
    private String requestUsername;

    /**
     * 请求模型
     */
    @ColumnType(length=255)
    @ColumnComment("请求模型")
    @TableField("model_id")
    private String modelId ;


}
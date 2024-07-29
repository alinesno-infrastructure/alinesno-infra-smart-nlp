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
 * 存储监控管理实体类
 * </p>
 * <p>
 * 该类继承自InfraBaseEntity，表示存储监控管理的基本信息。
 * </p>
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("monitor_manage")
@Data
public class MonitorManageEntity extends InfraBaseEntity {

    /**
     * 分组
     */
    @TableField("service_group")
	@ColumnType(length=32)
	@ColumnComment("分组")
    private String serviceGroup;

    /**
     * 服务名称
     */
    @TableField("service_name")
    @ColumnType(length=256)
    @ColumnComment("服务名称")
    private String serviceName;

    /**
     * 服务地址
     */
    @TableField("uri")
	@ColumnType(length=50)
	@ColumnComment("服务地址")
    private String uri;

    /**
     * 断言路径
     */
    @TableField("path")
	@ColumnType(length=255)
	@ColumnComment("断言路径")
    private String path ;

    /**
     * 今日请求总次数
     */
    @TableField("count")
    @ColumnType(length=255)
    @ColumnComment("今日请求总次数")
    private String count ;




}

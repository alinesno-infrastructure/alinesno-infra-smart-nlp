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
 * 存储开通服务实体类
 * </p>
 * <p>
 * 该类继承自InfraBaseEntity，表示存开通服务的基本信息。
 * </p>
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@TableName("open_service")
@Data
public class OpenServiceEntity extends InfraBaseEntity {


    /**
     * 图标
     */
    @TableField("icon")
    @ColumnType(length=32)
    @ColumnComment("图标")
    private String icon;

    /**
     * 服务名称
     */
    @TableField("service_name")
	@ColumnType(length=32)
	@ColumnComment("服务名称")
    private String serviceName;

    /**
     * 服务描述
     */
    @TableField("service_desc")
    @ColumnType(length=256)
    @ColumnComment("服务描述")
    private String serviceDesc;

    /**
     * 是否开启
     */
    @TableField("is_open")
	@ColumnComment("是否开启")
    private short isOpen;

    /**
     * 是否限流
     */
    @TableField("is_rate_limited")
    @ColumnComment("是否限流")
    private short isRateLimited ;

    /**
     * 请求次数
     */
    @TableField("request_count")
	@ColumnType(length=255)
	@ColumnComment("请求次数")
    private String requestCount ;

}

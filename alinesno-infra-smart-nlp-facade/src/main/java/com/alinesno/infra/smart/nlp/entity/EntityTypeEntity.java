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
@TableName("entity_type")
public class EntityTypeEntity extends InfraBaseEntity {
	/**
	 * 类型名称
	 */
	@ColumnType(length = 50)
	@ColumnComment("类型名称")
	@TableField("type_name")
	private String typeName;


}

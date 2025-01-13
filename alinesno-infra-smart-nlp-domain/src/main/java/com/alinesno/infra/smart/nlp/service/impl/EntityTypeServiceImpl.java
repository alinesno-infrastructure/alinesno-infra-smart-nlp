package com.alinesno.infra.smart.nlp.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.smart.nlp.entity.EntityTypeEntity;
import com.alinesno.infra.smart.nlp.mapper.EntityTypeMapper;
import com.alinesno.infra.smart.nlp.service.IEntityTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LuoXiaoDong
 * @version 1.0.0
 */
@Service
public class EntityTypeServiceImpl extends IBaseServiceImpl<EntityTypeEntity, EntityTypeMapper> implements IEntityTypeService {

	// 日志记录
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(EntityTypeServiceImpl.class);

}

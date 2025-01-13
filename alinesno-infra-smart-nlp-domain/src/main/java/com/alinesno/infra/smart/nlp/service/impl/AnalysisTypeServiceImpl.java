package com.alinesno.infra.smart.nlp.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.smart.nlp.entity.AnalysisTypeEntity;
import com.alinesno.infra.smart.nlp.mapper.AnalysisTypeMapper;
import com.alinesno.infra.smart.nlp.service.IAnalysisTypeService;
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
public class AnalysisTypeServiceImpl extends IBaseServiceImpl<AnalysisTypeEntity, AnalysisTypeMapper> implements IAnalysisTypeService {

	// 日志记录
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(AnalysisTypeServiceImpl.class);

}

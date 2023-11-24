package com.alinesno.infra.smart.nlp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alinesno.infra.smart.nlp.entity.LanguageEntity;
import com.alinesno.infra.smart.nlp.mapper.LanguageMapper;
import com.alinesno.infra.smart.nlp.service.ILanguageService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LuoXiaoDong
 * @version 1.0.0
 */
@Service
public class LanguageServiceImpl extends IBaseServiceImpl<LanguageEntity, LanguageMapper> implements ILanguageService {

	// 日志记录
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(LanguageServiceImpl.class);

}

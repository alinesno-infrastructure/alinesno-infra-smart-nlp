package com.alinesno.infra.smart.nlp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alinesno.infra.smart.nlp.entity.HttpRequestsEntity;
import com.alinesno.infra.smart.nlp.mapper.HttpRequestsMapper;
import com.alinesno.infra.smart.nlp.service.IHttpRequestsService;
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
public class HttpRequestsServiceImpl extends IBaseServiceImpl<HttpRequestsEntity, HttpRequestsMapper> implements IHttpRequestsService {

	// 日志记录
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(HttpRequestsServiceImpl.class);

}
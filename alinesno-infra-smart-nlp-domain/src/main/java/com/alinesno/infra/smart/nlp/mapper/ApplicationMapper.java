package com.alinesno.infra.smart.nlp.mapper;

import com.alinesno.infra.common.facade.mapper.repository.IBaseMapper;
import com.alinesno.infra.smart.nlp.entity.ApplicationEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplicationMapper extends IBaseMapper<ApplicationEntity> {
}

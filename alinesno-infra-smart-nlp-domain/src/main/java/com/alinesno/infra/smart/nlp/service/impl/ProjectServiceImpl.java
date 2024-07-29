package com.alinesno.infra.smart.nlp.service.impl;


import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.smart.nlp.entity.ProjectEntity;
import com.alinesno.infra.smart.nlp.mapper.ProjectMapper;
import com.alinesno.infra.smart.nlp.service.IProjectService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LuoXiaoDong
 * @version 1.0.0
 */
@Slf4j
@Service
public class ProjectServiceImpl extends IBaseServiceImpl<ProjectEntity, ProjectMapper> implements IProjectService {

    @Override
    public boolean updateOpenService(Long id, String openService) {
        UpdateWrapper<ProjectEntity> qw = new UpdateWrapper<>();
        qw.eq("id", id);
        qw.set("open_service",openService);
        boolean ifSuccess = this.update(qw);
        return ifSuccess ;

    }
}

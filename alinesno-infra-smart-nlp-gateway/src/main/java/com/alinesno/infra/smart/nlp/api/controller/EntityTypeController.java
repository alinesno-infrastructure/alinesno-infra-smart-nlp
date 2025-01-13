package com.alinesno.infra.smart.nlp.api.controller;

import com.alinesno.infra.common.core.constants.SpringInstanceScope;
import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.alinesno.infra.common.web.adapter.rest.BaseController;
import com.alinesno.infra.smart.nlp.entity.EntityTypeEntity;
import com.alinesno.infra.smart.nlp.service.IEntityTypeService;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理与EntityTypeEntity相关的请求的Controller。
 * 继承自BaseController类并实现IEntityTypeService接口。
 *
 * @author LuoXiaoDong
 * @version 1.0.0
 */
@Api(tags = "EntityType")
@RestController
@Scope(SpringInstanceScope.PROTOTYPE)
@RequestMapping("/api/infra/smart/nlp/entity_type")
public class EntityTypeController extends BaseController<EntityTypeEntity, IEntityTypeService> {

    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(EntityTypeController.class);

    @Autowired
    private IEntityTypeService service;

    /**
     * 获取EntityTypeEntity的DataTables数据。
     *
     * @param request HttpServletRequest对象。
     * @param model   Model对象。
     * @param page    DatatablesPageBean对象。
     * @return 包含DataTables数据的TableDataInfo对象。
     */
    @ResponseBody
    @PostMapping("/datatables")
    public TableDataInfo datatables(HttpServletRequest request, Model model, DatatablesPageBean page) {
        log.debug("page = {}", ToStringBuilder.reflectionToString(page));
        return this.toPage(model, this.getFeign(), page);
    }

    @Override
    public IEntityTypeService getFeign() {
        return this.service;
    }
}

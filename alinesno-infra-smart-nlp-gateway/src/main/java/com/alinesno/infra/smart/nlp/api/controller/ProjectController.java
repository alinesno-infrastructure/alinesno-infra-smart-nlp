package com.alinesno.infra.smart.nlp.api.controller;

import com.alinesno.infra.common.core.constants.SpringInstanceScope;
import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.common.web.adapter.rest.BaseController;
import com.alinesno.infra.smart.nlp.entity.ProjectEntity;
import com.alinesno.infra.smart.nlp.service.IProjectService;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 处理与ProjectEntity相关的请求的Controller。
 * 继承自BaseController类并实现IProjectService接口。
 *
 * @author LuoXiaoDong
 * @version 1.0.0
 */
@Api(tags = "Project")
@RestController
@Scope(SpringInstanceScope.PROTOTYPE)
@RequestMapping("/api/infra/smart/nlp/project")
public class ProjectController extends BaseController<ProjectEntity, IProjectService> {

    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private IProjectService service;

    /**
     * 获取ProjectEntity的DataTables数据。
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

    @ResponseBody
    @PostMapping("/updateOpenService")
    public AjaxResult updateDocumentType(HttpServletRequest request, @RequestBody ProjectEntity project) {
        log.debug("projectId = {} , documentType = {}", project.getId(),  project.getOpenService());
        boolean ifSuccess = service.updateOpenService(project.getId(), project.getOpenService());
        if ( ifSuccess ) {
            return AjaxResult.success();
        }else{
            return AjaxResult.error();
        }
    }


    @Override
    public IProjectService getFeign() {
        return this.service;
    }
}

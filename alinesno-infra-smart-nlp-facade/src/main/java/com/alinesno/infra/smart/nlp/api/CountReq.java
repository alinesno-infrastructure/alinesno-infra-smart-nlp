package com.alinesno.infra.smart.nlp.api;

import lombok.Data;

import java.util.List;

/**
 * @description
 * @author JL
 * @date 2020/07/09
 * @version v1.0.0
 */
@Data
public class CountReq implements java.io.Serializable {
    /**
     * 负载ID
     */
    private String balancedId;
    /**
     * 路由集合
     */
    private List<String> routeIds;
    /**
     * 统计类型
     */
    private String dateType;
    /**
     * 名称
     */
    private String name;
    /**
     * 日期
     */
    private String date;
    /**
     * 分组
     */
    private String groupCode;

    //  操作员
    String operatorId;

    private Integer currentPage;
    private Integer pageSize;
}

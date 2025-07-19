package com.deer.data.sync.admin.model;

/**
 * 表路由模型
 */
public class TableRoute {

    private Long id;

    /**自定义一个有意义的名字*/
    private String routeName;
    /**是否是提取其中一段*/
    private Boolean partiality;
    /**如果是整字段计算则为0，如果是提取其中一段，那么就是开始位置(包含)*/
    private Integer start;
    /**如果是整字段计算则为0，如果是提取其中一段，那么就是结束位置(包含)*/
    private Integer end;
    /**使用的算法*/
    private AlgorithmEnum algorithm;
}

package com.sdc.factor.entity.common.web;


import com.sdc.factor.entity.common.enums.HandlerClientType;

/**
 * 请求处理器，通常指的是controller类
 *
 * @author Sean
 * @since 2019-04-05
 */
public interface IRequestHandler {

    /**
     * 获得控制器的端类型
     */
    HandlerClientType type();
}

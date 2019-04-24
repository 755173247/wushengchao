package com.sdc.factor.entity.business.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.core.env.Environment;

/**
 * 公共业务类基类，封装对Spring Application上下文操作以及公共业务逻辑
 *
 * @author sean
 * @since 2018-12-06 18:22
 */
public abstract class AbstractBizSrv implements ApplicationContextAware, ApplicationEventPublisherAware, EnvironmentAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBizSrv.class);

    /**
     * spring容器
     */
    protected ApplicationContext applicationContext;

    /**
     * 事件发布器
     */
    protected ApplicationEventPublisher applicationEventPublisher;

    /**
     * 当前环境
     */
    protected Environment environment;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 发布事件，通知Spring容器内所有对该事件监听的监听者
     *
     * @param applicationEvent 应用事件
     */
    public void publishEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent != null) {
            this.applicationEventPublisher.publishEvent(applicationEvent);
        } else {
            LOGGER.error("Cannot publish null event to the spring container");
        }
    }
}

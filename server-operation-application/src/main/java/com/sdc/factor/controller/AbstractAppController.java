package com.sdc.factor.controller;

import com.sdc.factor.business.base.AbstractBizSrv;
import com.sdc.factor.common.enums.HandlerClientType;
import com.sdc.factor.common.web.IRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * APP端API基础控制器类
 *
 * @author Sean
 * @since 2019-04-05
 */
public abstract class AbstractAppController extends AbstractBizSrv implements IRequestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAppController.class);

    @Override
    public HandlerClientType type() {
        return HandlerClientType.MANAGEMENT;
    }
}

package com.sdc.factor.entity.configurations.component;

import com.sdc.factor.entity.common.beans.CommonSecurityProperties;
import com.sdc.factor.entity.common.entity.convert.PrivacyFieldConverter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * PrivacyFieldConverterInitializer 初始化程序
 * @author nicholas
 * @since 2018-12-10
 */
@Component
public class PrivacyFieldConverterInitializer implements ApplicationListener<ApplicationContextEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrivacyFieldConverterInitializer.class);

    @Autowired
    private CommonSecurityProperties securityProperties;

    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        //如果是刷新或者启动，设置加密盐至PrivacyFieldConverter的静态常量
        if (event instanceof ContextRefreshedEvent || event instanceof ContextStartedEvent) {
            //不为空则替换
            if (StringUtils.isNotBlank(this.securityProperties.getPrivacyFieldKey())) {
                LOGGER.debug("The privacy field key will use " + this.securityProperties.getPrivacyFieldKey() + " to instead the default value");
                PrivacyFieldConverter.ENCRYPT_KEY = this.securityProperties.getPrivacyFieldKey();
            }
        }
    }
}

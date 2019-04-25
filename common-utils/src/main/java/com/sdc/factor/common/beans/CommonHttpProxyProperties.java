package com.sdc.factor.common.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 通用HTTP代理配置
 *
 * @author Sean
 * @since 2019-04-05
 */
@Getter
@Setter
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "signin.common.proxy")
public class CommonHttpProxyProperties extends Proxy {

}

package com.sdc.factor.common.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 代理配置
 *
 * @author Sean
 * @since 2019-04-05
 */

@Getter
@Setter
@Accessors(chain = true)
public class Proxy {

    /**
     * 代理主机地址
     */
    private String host;

    /**
     * 代理端口
     */
    private int port = 3128;

    /**
     * 代理服务器用户的账户名
     */
    private String user;

    /**
     * 账户密码
     */
    private String password;

}

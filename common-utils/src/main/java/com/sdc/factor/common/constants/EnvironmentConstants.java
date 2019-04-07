package com.sdc.factor.common.constants;

/**
 * 系统支持的环境列表
 *
 * @author sean
 * @since 2019-01-01 15:01
 */
public interface EnvironmentConstants {

    //生产环境
    String PROD = "prod";

    //测试环境
    String TEST = "test";

    //开发环境
    String DEV = "dev";

    //受限测试环境、准生产环境
    String STAGING = "staging";

    //支持的环境列表
    String[] ENVIRONMENTS = new String[]{DEV, TEST, STAGING, PROD};
}

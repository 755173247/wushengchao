package com.sdc.factor.entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 运营管理平台入口
 *
 * @author Sean
 * @since 2019-04-20
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@EnableCaching
@EnableAsync
public class OperationAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationAdminApplication.class, args);
    }
}

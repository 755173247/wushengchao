package com.sdc.factor.common.enums;

import java.io.Serializable;

/**
 * <p>
 * 自定义枚举接口
 * </p>
 *
 * @author hubin
 * @since 2017-10-11
 */
public interface IEnum<T extends Serializable> {

    /**
     * 枚举数据库存储值
     */
    T getValue();

}
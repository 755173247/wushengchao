package com.sdc.factor.third.entity.business;

import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 请求表
 * @author wushengchao
 * @create 2019-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table
@ToString
public class LogRequest extends BaseModel {
    @Id
    @GeneratedValue(generator = "hilo")
    private Long logRequestId;
    /** 供货商名称 */
    @Column(nullable = false)
    private String vendorName;
    /** 请求路径 */
    @Column(nullable = false)
    private String logRequestUrl;
    /** 请求参数 */
    @Column(nullable = false)
    private String logRequestParams;
    /** 序列号 */
    @Column(nullable = false)
    private String serializableNumber;
    /** api接口标识码 */
    @Column(nullable = false)
    private String serviceCode;
}

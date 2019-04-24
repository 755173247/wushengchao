package com.sdc.factor.third.entity.business;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 响应表
 * @author wushengchao
 * @create 2019-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table
@ToString
public class LogResponse {
    @Id
    @GeneratedValue(generator = "hilo")
    private Long logResponseId;
    /** 错误码 */
    @Column(nullable = false)
    private String errorCode;
    /** 错误码消息描述 */
    @Column(nullable = false)
    private String errorMessage;
    /** 供货商名称 */
    @Column(nullable = false)
    private String vendorName;
    /** 响应数据 */
    @Column(nullable = false)
    private String data;
    /** 序列号 */
    @Column(nullable = false)
    private String serializableNumber;
    /** api接口标识码 */
    @Column(nullable = false)
    private String serviceCode;

}

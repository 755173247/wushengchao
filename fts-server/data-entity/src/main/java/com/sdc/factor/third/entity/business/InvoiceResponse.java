package com.sdc.factor.third.entity.business;

import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author wushengchao
 * @create 2019-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table
@ToString
public class InvoiceResponse extends BaseModel {
    @Id
    @GeneratedValue(generator = "hilo")
    private Long invoiceResponseId;
    /** 状态码 */
    @Column(nullable = false)
    private Integer status;
    /** 状态描述 */
    @Column(nullable = false)
    private String description;
    /** 发票参数来源 */
    @Column(nullable = false)
    private String invoiceParamsSource;
    /** 图片 */
    @Column(nullable = false)
    private String picture;
    /** 批次号 */
    @Column(nullable = false)
    private String batchNumber;
    /** 序列号 */
    @Column(nullable = false)
    private String serializableNumber;
    /** api接口标识码 */
    @Column(nullable = false)
    private String serviceCode;
}

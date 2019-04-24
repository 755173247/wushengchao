package com.sdc.factor.third.entity.business;

import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * 发票详情表
 * @author wushengchao
 * @create 2019-04-22
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table
@ToString
public class InvoiceBusiness extends BaseModel {
    @Id
    @GeneratedValue(generator = "hilo")
    private Long invoiceBusinessId;
    /** 发票流水号 */
    @Column(nullable = false)
    private String serialNo;
    /** 发票代码 */
    @Column(nullable = false)
    private String invoiceCode;
    /** 发票号码 */
    @Column(nullable = false)
    private String invoiceNo;
    /** 开票日期 */
    @Column(nullable = false)
    private DateTime invoiceDate;
    /** 发票金额(不含税) */
    @Column(nullable = false)
    private Double invoiceMoney;
    /** 校验码 */
    @Column(nullable = false)
    private String checkCode;
    /** 销方名称 */
    @Column(nullable = false)
    private String salerName;
    /** 销方纳税人识别号 */
    @Column(nullable = false)
    private String salerTaxNo;
    /** 销方地址电话 */
    @Column(nullable = false)
    private String salerAddressPhone;
    /** 销方开户行及账号 */
    @Column(nullable = false)
    private String salerAccount;
    /** 购方名称 */
    @Column(nullable = false)
    private String buyerName;
    /** 购方纳税人识别号 */
    @Column(nullable = false)
    private String buyerTaxNo;
    /** 购方地址电话 */
    @Column(nullable = false)
    private String buyerAddressPhone;
    /** 购方银行账号 */
    @Column(nullable = false)
    private String buyerAccount;
    /** 金额合计(不含税) */
    @Column(nullable = false)
    private Double amount;
    /** 税额合计 */
    @Column(nullable = false)
    private Double taxAmount;
    /** 价税合计(小写) */
    @Column(nullable = false)
    private Double totalAmount;
    /** 价税合计大写 */
    @Column(nullable = false)
    private String totalAmountCn;
    /** 备注 */
    @Column(nullable = false)
    private String remark;
    /** 机器编号 */
    @Column(nullable = false)
    private String machineNo;
    /** 作废标识，N：未作废；Y：作废 */
    @Column(nullable = false)
    private String cancelMark;
    /** 代开标识：默认空，1为代开 */
    @Column(nullable = false)
    private String proxyMark;
    /** 快照地址 */
    @Column(nullable = false)
    private String snapshotUrl;
    /** 原件下载地址 */
    @Column(nullable = false)
    private String downloadUrl;
    /** 发票种类 */
    @Column(nullable = false)
    private String invoiceType;
    /** 开票人 */
    @Column(nullable = false)
    private String drawer;
    /** 复核人 */
    @Column(nullable = false)
    private String reviewer;
    /** 收款人 */
    @Column(nullable = false)
    private String payee;
    /** 查验次数 */
    @Column(nullable = false)
    private Integer checkCount;
    /** 序列号 */
    @Column(nullable = false)
    private String serializableNumber;
    /** api接口标识码 */
    @Column(nullable = false)
    private String serviceCode;
}

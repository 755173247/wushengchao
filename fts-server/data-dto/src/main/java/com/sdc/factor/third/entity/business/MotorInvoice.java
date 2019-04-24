package com.sdc.factor.third.entity.business;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;

/**
 * 机动车发票信息
 * @author wushengchao
 * @create 2019-04-23
 */
@Getter
@Setter
@Data
public class MotorInvoice implements Serializable {
    private static final long serialVersionUID = 3734455842283411539L;
    private String invoiceCode;
    private String invoiceNo;
    private String invoiceDate;
    private String buyerTaxNo;
    private String buyerName;
    private String buyerIdNo;
    private String salerName;
    private String salerPhoneNumber;
    private String salerTaxNo;
    private String salerAccount;
    private String salerAddress;
    private String salerBankName;
    private String vehicleType;
    private String bandModel;
    private String produceArea;
    private String qualifiedNo;
    private String certificateNo;
    private String commodityInspectionNo;
    private String engineNo;
    private String vehicleIdentificationNo;
    private Double taxRate;
    private Double taxAccount;
    private Double totalAmount;
    private String taxAuthorityCode;
    private String taxAuthorityName;
    private Double amount;
    private Double tonnage;
    private Integer limitedCount;
    private String machineNo;
    private String taxPaymentCertificateNo;
    private String cancelMark;
}

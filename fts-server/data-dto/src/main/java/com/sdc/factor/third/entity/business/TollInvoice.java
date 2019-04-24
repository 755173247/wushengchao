package com.sdc.factor.third.entity.business;

import java.io.Serializable;

/**
 * 通行费发票信息
 * @author wushengchao
 * @create 2019-04-23
 */
public class TollInvoice implements Serializable {
    private static final long serialVersionUID = 6504062629636929510L;
    private String serialNo;
    private String invoiceCode;
    private String invoiceNo;
    private String invoiceDate;
    private String invoiceMoney;
    private String checkCode;
    private String salerName;
    private String salerTaxNo;
    private String salerAddressPhone;
    private String salerAccount;
    private String buyerName;
    private String buyerTaxNo;
    private String buyerAddressPhone;
    private String buyerAccount;
    private String amount;
    private String taxAmount;
    private String totalAmount;
    private String totalAmountCn;
    private String remark;
    private String machineNo;
    private String cancelMark;
    private String proxyMark;
    private String snapshotUrl;
    private String downloadUrl;
    private String invoiceType;
    private String drawer;
    private String reviewer;
    private String payee;
    private String checkCount;
}

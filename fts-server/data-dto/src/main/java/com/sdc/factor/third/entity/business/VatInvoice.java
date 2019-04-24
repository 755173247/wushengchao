package com.sdc.factor.third.entity.business;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;

/**
 * 增值税发票信息
 * @author wushengchao
 * @create 2019-04-23
 */
@Getter
@Setter
@Data
public class VatInvoice implements Serializable {
    private static final long serialVersionUID = 7330865821165307404L;
    private String serialNo;
    private String invoiceCode;
    private String invoiceNo;
    private String invoiceDate;
    private String checkCode;
    private String salerName;
    private String salerTaxNo;
    private String salerAddressPhone;
    private String salerAccount;
    private String buyerName;
    private String buyerTaxNo;
    private String buyerAddressPhone;
    private String buyerAccount;
    private Double amount;
    private Double taxAmount;
    private Double totalAmount;
    private Double totalAmountCn;
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
    private Integer checkCount;
    private List<VatItemsInvoice> listVatItemsInvoice;
}

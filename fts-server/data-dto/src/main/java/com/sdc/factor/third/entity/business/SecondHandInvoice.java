package com.sdc.factor.third.entity.business;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;

/**
 * 二手车发票信息
 * @author wushengchao
 * @create 2019-04-23
 */
@Getter
@Setter
@Data
public class SecondHandInvoice implements Serializable {
    private static final long serialVersionUID = -1888195836087867795L;
    private String serialNo;
    private String invoiceCode;
    private String invoiceNo;
    private String  invoiceDate;
    private String buyerPhoneNumber;
    private String buyerName;
    private String buyerIdNo;
    private String buyerAddress;
    private String salerName;
    private String salerPhoneNumber;
    private String salerIdNO;
    private String salerAddress;
    private String licensePlateNumber;
    private String registrationNumber;
    private String vehicleType;
    private String vehicleIdentificationNo;
    private String bandModel;
    private String vehicleManagementName;
    private Double totalAmount;
    private String totalAmountCn;
    private String auctionName;
    private String auctionPhoneNumber;
    private String auctionAddress;
    private String auctionTaxpayerId;
    private String auctionBankAccout;
    private String marketName;
    private String marketPhoneNumber;
    private String marketAddress;
    private String marketTaxpayerId;
    private String marketBankAccout;
    private String remark;
    private Integer checkCount;
    private String cancelMark;
    private String invoiceType;
}

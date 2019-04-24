package com.sdc.factor.third.entity.business;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;

/**
 * 通行费发票条目信息
 * @author wushengchao
 * @create 2019-04-23
 */
@Getter
@Setter
@Data
public class TollItemsInvoice implements Serializable {
    private static final long serialVersionUID = -8166931093099197088L;
    private String specModel;
    private Double taxRate;
    private Double detailAmount;
    private String licensePlateNumber;
    private String goodsName;
    private String  startDate;
    private String endDate;
    private Double taxAmount;
}

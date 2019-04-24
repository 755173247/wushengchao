package com.sdc.factor.third.entity.business;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 增值税发票条目信息
 * @author wushengchao
 * @create 2019-04-23
 */
@Getter
@Setter
@Data
public class VatItemsInvoice implements Serializable {
    private static final long serialVersionUID = 337537967489022303L;
    private String goodsName;
    private Double unitPrice;
    private Integer num;
    private Double taxRate;
    private String unit;
    private Double detailAmount;
    private String specModel;
    private Double taxAmount;
    private String goodsCode;
}

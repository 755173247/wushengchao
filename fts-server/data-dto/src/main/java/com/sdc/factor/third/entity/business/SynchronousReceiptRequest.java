package com.sdc.factor.third.entity.business;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wushengchao
 * @create 2019-04-23
 */
@Getter
@Setter
@Data
public class SynchronousReceiptRequest implements Serializable {
    private static final long serialVersionUID = 8768155603103389015L;
    @NotNull(message="invoiceCode不能为空")
    private String invoiceCode;
    @NotNull(message="invoiceNo不能为空")
    private String invoiceNo;
    @NotNull(message="invoiceDate不能为空")
    private String invoiceDate;
    private Double invoiceMoney;
    private String checkCode;
    private String isCreateUrl;
}

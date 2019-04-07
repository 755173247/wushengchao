package com.sdc.factor.business.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 统计资产返回结果VO
 * 
 * @author Sean
 * @since 2019-03-24
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class FtsAssetStatVo implements Serializable {

    /** 原始条数 */
    private Long rawCount = 0l;

    /** 原始合计债权金额 */
    private BigDecimal rawAmount = BigDecimal.ZERO;

    /** 筛选后合格条数 */
    private Long okCount = 0l;

    /** 筛选后合格合计债权金额 */
    private BigDecimal okAmount = BigDecimal.ZERO;

    /** 筛选后不合格条数 */
    private Long noCount = 0l;

    /** 筛选后不合格合计债权金额 */
    private BigDecimal noAmount = BigDecimal.ZERO;

    /** 待筛选中条数 */
    private Long ingCount = 0l;

    /** 待筛选中合计债权金额 */
    private BigDecimal ingAmount = BigDecimal.ZERO;
}

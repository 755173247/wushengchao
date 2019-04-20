package com.sdc.factor.business.entity;

import com.sdc.factor.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 再融资计划表
 * 
 * @author Sean
 * @since 2019-03-24
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table
@ToString
public class FtsRefinPlan extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long refinPlanId;

    /** 项目Id */
    @Column(nullable = false)
    private Long projId;

    /** 融资计划名称 */
    @Column(length = 256)
    private String refinPlanName;

    /** 发行载体 */
    @Column(length = 256)
    private String issCarrier;

    /** 登记托管机构 */
    @Column(length = 256)
    private String hostingOrg;

    /** 发行模式0储架or1单次） */
    @Column
    private String issMode;

    /** 产品Id */
    @Column
    private Long prodId;

    /** 产品名称 */
    @Column(length = 256)
    private String prodName;

    /** 产品类型 */
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private FtsProd.FtsProdType prodType;

    /** 备案总额（元/人民币） */
    @Column(columnDefinition = "numeric(19,2) NOT NULL default 0")
    private BigDecimal filingAmt = BigDecimal.ZERO;

    /** 发行总额（元/人民币） */
    @Column(columnDefinition = "numeric(19,2) NOT NULL default 0")
    private BigDecimal issAmt = BigDecimal.ZERO;

    /** 优先级份额（元/人民币） */
    @Column(columnDefinition = "numeric(19,2) NOT NULL default 0")
    private BigDecimal priorityAmt = BigDecimal.ZERO;

    /** 次级份额（元/人民币） */
    @Column(columnDefinition = "numeric(19,2) NOT NULL default 0")
    private BigDecimal secondaryAmt = BigDecimal.ZERO;

    /** 融资计划期限 */
    @Column
    private Integer months;

    /** 融资计划发行日 */
    @Column
    private Date issDate;

    /** 融资计划到期日 */
    @Column
    private Date endDate;

    /** 票面利率 */
    @Column(columnDefinition = "numeric(19,2) NOT NULL default 0")
    private BigDecimal billRate = BigDecimal.ZERO;

    /** 外部增信方式 */
    @Column(length = 256)
    private String addTrust;

    /** 计划信用评级 */
    @Column(length = 256)
    private String trustLevel;

    /** 循环购买频次(月/次) */
    @Column
    private Integer buyNum;

    /** 本金摊还频次(月/次) */
    @Column
    private Integer returnAmtNum;

    /** 再融资附件分组 */
    @Column(length = 32)
    private String refinAccy;

    @Transient
    private String prodTypeName;

    public String getProdTypeName() {
        if (this.prodType == FtsProd.FtsProdType.MANY_ONE) {
            return "反向保理";
        } else if (this.prodType == FtsProd.FtsProdType.ONE_MANY) {
            return "正向保理";
        }
        return "";
    }
}
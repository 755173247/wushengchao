package com.sdc.factor.business.entity;

import com.sdc.factor.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 
 * 融资申请单,资产记录表
 * 
 * @author Sean
 * @since 2019-03-24
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "projId") })
@ToString
public class FtsFinAsset extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long finAssetId;

    /** 关联融资申请Id */
    @Column(nullable = false)
    private Long finId;

    /** 融资申请单编号 */
    @Column(nullable = false, length = 32)
    private String finNum;

    /** 关联项目Id */
    @Column(nullable = false)
    private Long projId;

    /** 项目编号 */
    @Column(nullable = false, length = 32)
    private String projNum;

    /** 项目名称 */
    @Column(nullable = false, length = 256)
    private String projName;

    /** 融资申请金额 */
    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    /** 关联资产Id */
    @Column
    private Long assetId;

    /** 单据编号 */
    @Column(length = 256)
    private String assetBillNum;

    /** 管理合同Id */
    @Column
    private Long contractId;

    /** 申请企业 */
    @Column
    private Long confirmEntId;
}

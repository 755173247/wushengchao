package com.sdc.factor.entity.business.entity;

import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 再融资参与机构
 * 
 * @author CTJ/HZY
 *
 */
@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table
@ToString
public class FtsAssetPool extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long assetPoolId;

    /** 项目Id */
    @Column(nullable = false)
    private Long projId;

    /** 项目名称 */
    @Column(length = 256)
    private String projName;

    /** 资产类别 */
    @Column(length = 256)
    private String assetType;

    /** 资产总额(元） */
    @Column(columnDefinition = "numeric(19,2) NOT NULL default 0")
    private BigDecimal assetAmt = BigDecimal.ZERO;

    /** 资产笔数 */
    @Column
    private Integer assetNumber;

    /** 原始债权人数量 */
    @Column
    private Integer debteeNumber;

    /** 债务人数量 */
    @Column
    private Integer obligorNumber;
}

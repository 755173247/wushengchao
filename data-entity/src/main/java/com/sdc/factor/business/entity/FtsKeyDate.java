package com.sdc.factor.business.entity;

import com.sdc.factor.common.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 再融资:关键日期
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table
public class FtsKeyDate extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long keyDateId;

    /** 项目Id */
    @Column(nullable = false)
    private Long projId;

    /** 项目名称 */
    @Column(length = 256)
    private String projName;

    /** 初始资产封包日 */
    @Column
    private Date assPacketDate;

    /** 信托生效日 */
    @Column
    private Date trustEffectDate;

    /** 回收款转付日 */
    @Column(length = 256)
    private String assTransferDate;

    /** 循环购买执行日 */
    @Column(length = 256)
    private String loopBuyDate;

    /** 应收账款到期日 */
    @Column(length = 256)
    private String debtDueDate;

    /** 兑付日 */
    @Column(length = 256)
    private String redempDate;
}

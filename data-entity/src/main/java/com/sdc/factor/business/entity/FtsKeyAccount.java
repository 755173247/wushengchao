package com.sdc.factor.business.entity;

import com.sdc.factor.common.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 再融资:关键账户
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table
public class FtsKeyAccount extends BaseModel {

    /** 账户类型 */
    public enum FtsAccType {
        /** 监管账户（鼎程保理） */
        FACTOR,
        /** 信托专户（发行载体 ） */
        TRUST,
        /** 募集专用账户（主承销商） */
        MAIN_UW,
        /** 转让价款收款账户（融资人） */
        FINMAN,
        /** 回收款归集账户 */
        RECYCLE;
    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long keyAccountId;

    /** 项目Id */
    @Column(nullable = false)
    private Long projId;

    /** 项目名称 */
    @Column(length = 256)
    private String projName;

    /** 户名 */
    @Column(length = 256)
    private String accName;

    /** 账号 */
    @Column(length = 256)
    private String accNum;

    /** 开户行 */
    @Column(length = 256)
    private String bankName;

    /** 人行支付系统号 */
    @Column(length = 256)
    private String paySysNum;

    /** 账户类型 */
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private FtsAccType accType;
}

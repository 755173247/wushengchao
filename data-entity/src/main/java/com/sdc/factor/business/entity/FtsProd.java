package com.sdc.factor.business.entity;

import com.sdc.factor.common.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 产品信息
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "prodName", unique = true) })
public class FtsProd extends BaseModel {

    /** 产品类型 */
    public enum FtsProdType {
        /** 1+N */
        ONE_MANY,
        /** N+1 */
        MANY_ONE
    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long prodId;

    /** 产品名称 */
    @Column(nullable = false, length = 256)
    private String prodName;

    /** 产品类型 */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsProdType prodType;

    /** 0明保理/1暗保理 */
    @Column(nullable = false, columnDefinition = "char(1) NOT NULL default 0")
    private Boolean factorType;

    @Transient
    private final List<FtsProdAttr> attrList = new ArrayList<>();

    @Transient
    private final List<FtsProdProt> protList = new ArrayList<>();
}

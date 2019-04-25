package com.sdc.factor.business.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 
 * 项目要素/项目属性
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "projId") })
@ToString
public class FtsProjAttr extends FtsProdAttrBase {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long projAttrId;

    /** 要素值 */
    @Column(length = 2048)
    private String attrValue;

    /** 产品要素Id */
    @Column(nullable = false)
    private Long prodAttrId;

    /** 关联项目Id */
    @Column(nullable = false)
    private Long projId;

    /** 关联项目名称 */
    @Column(nullable = false, length = 256)
    private String projName;
}

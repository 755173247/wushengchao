package com.sdc.factor.business.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 
 * 产品要素/产品属性
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "prodId") })
public class FtsProdAttr extends FtsProdAttrBase {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long prodAttrId;
}

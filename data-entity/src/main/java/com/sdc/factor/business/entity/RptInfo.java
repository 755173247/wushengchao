package com.sdc.factor.business.entity;

import com.sdc.factor.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 自定义报表
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table
@ToString
public class RptInfo extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long infoId;

    /** 合计查询使用明细表Id */
    @Column(columnDefinition = "int8 NOT NULL default 0")
    private Long parentId;

    /** 报表名称 */
    @Column(nullable = false, length = 128)
    private String infoName;

    /** 报表sql */
    @Lob
    @Column()
    private String infoSql;

    /** 顺序 */
    private Integer infoOrd;
}

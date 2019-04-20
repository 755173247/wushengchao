package com.sdc.factor.business.entity;

import com.sdc.factor.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 再融资关键条款
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
public class FtsKeyClause extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long keyClauseId;

    /** 项目Id */
    @Column(nullable = false)
    private Long projId;

    /** 项目名称 */
    @Column(length = 256)
    private String projName;

    /** 关键条款类型 */
    @Column(length = 256)
    private String clauseType;

    /** 关键条款内容 */
    @Column(length = 2048)
    @Lob
    private String clauseContent;
}

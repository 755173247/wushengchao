package com.sdc.factor.business.entity;

import com.sdc.factor.common.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 合同签订方
 *
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "contractId,entId"), @Index(columnList = "projId") })
@ToString
public class FtsSigner extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long signerId;

    /** 项目Id */
    @Column
    private Long projId;

    /** 合同Id */
    @Column(nullable = false)
    private Long contractId;

    /** 企业Id */
    @Column(nullable = false)
    private Long entId;

    /** 企业名称 */
    @Column(nullable = false, length = 256)
    private String entName;

    /** 是否签订 */
    @Column(nullable = false)
    private Boolean signed;

    /** 项目角色 */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsProjEnt.FtsProjRole projRole;

    /** 协议类型 */
    @Column(length = 32, columnDefinition = "varchar(32) NOT NULL default 'COMMON'")
    @Enumerated(EnumType.STRING)
    private FtsProdProt.FtsProtType protType;

    /** 企业信息 */
    @Transient
    private FtsEnt ent;

    @Transient
    private boolean assetMached;
}

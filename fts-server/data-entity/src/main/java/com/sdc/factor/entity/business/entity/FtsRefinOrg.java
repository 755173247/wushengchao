package com.sdc.factor.entity.business.entity;


import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 再融资参与机构
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
public class FtsRefinOrg extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long refinOrgId;

    /** 项目Id */
    @Column(nullable = false)
    private Long projId;

    /** 核心企业Id */
    @Column
    private Long coreId;

    /** 核心企业名称 */
    @Column(length = 256)
    private String coreName;

    /** 增信主体 */
    @Column(length = 256)
    private String trustBody;

    /** 资产服务机构一 */
    @Column(length = 256)
    private String serveOrgOne;

    /** 资产服务机构二 */
    @Column(length = 256)
    private String serveOrgTwo;

    /** 主承销商Id */
    @Column
    private Long mainUwId;

    /** 主承销商名称 */
    @Column(length = 256)
    private String mainUwName;

    /** 信托机构Id */
    @Column
    private Long trustId;

    /** 发行载体管理机构 */
    @Column(length = 256)
    private String trustName;

    /** 联席承销商 */
    @Column(length = 256)
    private String unionUw;

    /** 监管银行 */
    @Column(length = 256)
    private String superviseBank;

    /** 保管银行 */
    @Column(length = 256)
    private String custodyBank;

    /** 法律顾问 */
    @Column(length = 256)
    private String lawer;

    /** 评级机构 */
    @Column(length = 256)
    private String evalOrg;

    /** 会计师事务所 */
    @Column(length = 256)
    private String accOrg;

    /** 投资人-优先级 */
    @Column(length = 256)
    private String priInvestor;

    /** 投资人-次级 */
    @Column(length = 256)
    private String secInvestor;
}
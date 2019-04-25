package com.sdc.factor.business.entity;

import com.sdc.factor.common.annotations.ContractVar;
import com.sdc.factor.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 项目信息
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
public class FtsProj extends BaseModel {

    /**
     * 项目权限
     */
    public enum FtsProjRight {
        /**
         * 无权限
         */
        NULL,
        /**
         * 查看权限
         */
        VIEW,
        /**
         * 编辑权限
         */
        EDIT,
    }

    /**
     * 项目状态
     */
    public enum FtsProjStatus {
        /**
         * 待提交
         */
        DRAFT,
        /**
         * 待审核
         */
        APPRING,
        /**
         * 已驳回
         */
        REJECT,
        /**
         * 待提供资产
         */
        PROVIDE_ASSET_ING,
        /**
         * 待筛选资产
         */
        FILTER_ASSET_ING,
        /**
         * 待确认资产
         */
        CONFIRM_ASSET_ING,
        /**
         * 项目执行中
         */
        EXEC_ING,
        /**
         * 已完成
         */
        FINISHED;

        /**
         * 判断某个状态是否可以邀约
         */
        public static boolean isInvitable(FtsProjStatus status) {
            return status == PROVIDE_ASSET_ING || status == FILTER_ASSET_ING || CONFIRM_ASSET_ING == status;
        }

        /**
         * 判断某个状态是否可以邀约
         */
        public static boolean isInvitable(FtsProj proj) {
            return proj != null && isInvitable(proj.getProjStatus());
        }

        /**
         * 资产已经确认
         */
        public static boolean isAssetConfirmed(FtsProjStatus status) {
            return status == CONFIRM_ASSET_ING || status == EXEC_ING || FINISHED == status;
        }

        public static boolean isAssetConfirmed(FtsProj proj) {
            return proj != null && isAssetConfirmed(proj.getProjStatus());
        }
    }

    /**
     * 判断状态信息的颜色(根据枚举类型进行验证)
     * @return
     */
    public String getProjBadge() {
        if (projStatus == FtsProjStatus.DRAFT) {
            return "badge-draft";
        } else if (projStatus == FtsProjStatus.REJECT) {
            return "badge-reject";
        } else if (projStatus == FtsProjStatus.APPRING) {
            return "badge-appring";
        } else if (projStatus == FtsProjStatus.PROVIDE_ASSET_ING) {
            return "badge-provide-asset-ing";
        } else if (projStatus == FtsProjStatus.FILTER_ASSET_ING) {
            return "badge-filter-asset-ing";
        } else if (projStatus == FtsProjStatus.CONFIRM_ASSET_ING) {
            return "badge-confirm-asset-ing";
        } else if (projStatus == FtsProjStatus.EXEC_ING) {
            return "badge-exec-ing";
        }
        return "badge-finished";
    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long projId;

    @ContractVar(name = "项目名称")
    /** 项目名称 */
    @Column(nullable = false, length = 256)
    private String projName;

    /**
     * 项目编号
     */
    @Column(nullable = false, length = 32)
    private String projNum;

    /**
     * 债务人ID/核心企业Id
     */
    @Column(nullable = false)
    private Long coreId;

    @ContractVar(name = "核心企业名称")
    /** 债务人名称/核心企业名称 */
    @Column(nullable = false, length = 256)
    private String coreName;

    /**
     * 产品Id
     */
    @Column(nullable = false)
    private Long prodId;

    @ContractVar(name = "产品名称")
    /** 产品名称 */
    @Column(nullable = false, length = 256)
    private String prodName;

    /**
     * 产品类型
     */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsProd.FtsProdType prodType;

    @ContractVar(name = "融资金额")
    /** 融资金额 */
    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @ContractVar(name = "到期日期")
    /** 到期日期 */
    @Column(nullable = false)
    private Date dueDate;

    /**
     * 项目状态
     */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsProjStatus projStatus;

    /**
     * 资产初始起算日/封包日
     */
    @Column
    private Date assetPkgDate;

    /**
     * 清算所
     */
    @Column(length = 256)
    private String clearOrg;

    /**
     * 评级机构
     */
    @Column(length = 256)
    private String evalOrg;

    /**
     * 法律顾问
     */
    @Column(length = 256)
    private String lawer;

    /**
     * 发行日
     */
    @Column
    private Date releaseDate;

    /**
     * 信托生效日
     */
    @Column
    private Date trustStartDate;

    /**
     * 信托终止日
     */
    @Column
    private Date trustEndDate;

    /**
     * 综合折价率
     */
    @Column(nullable = false, columnDefinition = "numeric(19,2) NOT NULL default 0")
    private BigDecimal discountRate = BigDecimal.ZERO;

    /**
     * 应收账款转让日/应收账款债权购买价款支付日
     */
    @Column
    private Date assetTransDate;

    /**
     * 再融资机构Id
     */
    @Column
    private Long refinId;

    /**
     * 再融资机构名称
     */
    @Column(length = 256)
    private String refinName;

    /**
     * 信托机构Id
     */
    @Column
    private Long trustId;

    /**
     * 信托机构名称、受托人
     */
    @Column(length = 256)
    private String trustName;

    /**
     * 主承销商Id
     */
    @Column
    private Long mainUwId;

    /**
     * 主承销商名称
     */
    @Column(length = 256)
    private String mainUwName;

    /**
     * 是否开启安心签 0关闭 1开启
     */
    @Column(nullable = false, columnDefinition = "char(1) NOT NULL default 1")
    private Boolean cfcaBtn;

    /**
     * 信托名称
     */
    @Column(length = 256)
    private String trustProjName;

    /**
     * 信托计划
     */
    @Column(length = 256)
    private String trustPlanName;

    /**
     * 受托人Id/保理商Id：立项时，默认带出保理商ID
     */
    @Column
    private Long factorId;

    /**
     * 受托人名称/保理商名称：立项时，默认带出保理商名称
     */
    @Column(length = 256)
    private String factorName;

    /**
     * 资产服务机构：立项时，默认带出保理商名称
     */
    @Column(length = 256)
    private String assetSrvOrg;

    /**
     * 资产属性
     */
    @Lob
    @Column
    String assetFields;

    /**
     * 项目附件 , 关联CatiiFile.fileGroup
     */
    @Column(length = 32)
    private String projAnnex;

    /**
     * 融资是否追加，0无追，1有追
     */
    @Column(nullable = false, columnDefinition = "char(1) NOT NULL default 0")
    private Boolean finAppend;

    @Transient
    private List<FtsProjStatus> projStatusList;

    @Transient
    private String createName;

    @Transient
    private String str2;

    /**
     * 合同、转让通知书、付款确认函、当前登录人签订数量
     */
    @Transient
    private int signOK;

    /**
     * 合同、转让通知书、付款确认函、当前登录人待签订数量
     */
    @Transient
    private int signNO;

    /**
     * 合同、当前登录人待签订数量
     */
    @Transient
    private int signING;

    /**
     * 合同类型
     */
    @Transient
    @Enumerated(EnumType.STRING)
    private FtsProdProt.FtsProtType protType;

    /**
     * 合同查询
     */
    @Transient
    private Boolean isProtocolPage = false;

    /**
     * 折价率显示值
     */
    @Transient
    public String getRateStr() {
        return (this.discountRate == null ? 0d : this.discountRate.doubleValue()) * 100 + "%";
    }

    public void doProjAttr(FtsEnt ent) {
        if (this.projId == null) {
            this.factorId = ent.getEntId();
            this.factorName = ent.getEntName();
            this.assetSrvOrg = ent.getEntName();
        }
    }
}

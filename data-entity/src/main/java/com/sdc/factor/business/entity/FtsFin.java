package com.sdc.factor.business.entity;

import com.sdc.factor.common.annotations.ContractVar;
import com.sdc.factor.common.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * 融资申请单
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "projId"), @Index(columnList = "assetId", unique = true) })
@ToString
public class FtsFin extends BaseModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(FtsFin.class);

    /** 融资申请单状态 */
    public enum FtsFinStatus {
        /** 待申请 */
        APPLYING,
        /** 待审批 */
        APPRING,
        /** 待确认 */
        CFRMING,
        /** 已拒绝 */
        REJECT,
        /** 已确认 */
        CFRM,
    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long finId;

    /** 融资申请单编号 */
    @Column(nullable = false, length = 32)
    private String finNum;

    /** 关联项目Id */
    @Column(nullable = false)
    private Long projId;

    /** 项目编号 */
    @Column(nullable = false, length = 32)
    private String projNum;

    /** 项目名称 */
    @Column(nullable = false, length = 256)
    private String projName;

    /** 申请企业 */
    @Column(nullable = false)
    private Long appyEntId;

    /** 申请企业名称 */
    @Column(nullable = false, length = 256)
    private String appyEntName;

    /** 确认企业Id */
    @Column
    private Long confirmEntId;

    /** 确认企业 */
    @Column(length = 256)
    private String confirmEntName;

    /** 债务人ID/核心企业Id */
    @Column(nullable = false)
    private Long coreId;

    /** 债务人名称/核心企业名称 */
    @Column(nullable = false, length = 256)
    private String coreName;

    /** 应收账款金额 */
    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsFinStatus status;

    /** 关联资产Id */
    @Column
    private Long assetId;

    /** 单据编号 */
    @Column(length = 256)
    private String assetBillNum;

    @ContractVar(name = "折价率/融资比列")
    /** 折价率/融资比列 */
    @Column
    private String discountRate;

    @ContractVar(name = "融资金额")
    /** 融资金额 */
    @Column
    private BigDecimal transAmt;

    /** 产品类型 */
    @Transient
    private FtsProd prod;

    /** 折价率显示值 */
    @Transient
    public String getRateStr() {
        String percentageNumber = "0";
        if (!StringUtils.isBlank(this.discountRate)) {
            try {
                Double doubleVal = Double.valueOf(this.discountRate.trim());
                percentageNumber = String.valueOf(doubleVal * 100);
            } catch (Exception e) {
                LOGGER.error("Fail to convert string value " + this.discountRate + " to double", e);
            }
        }
        return percentageNumber + "%";
    }

    @Transient
    private boolean isApply = true;

    @Transient
    private List<FtsFinStatus> statusList;

    public String getFinLinkCss() {
        if (status == FtsFinStatus.APPRING) {
            return "badge-appring";
        } else if (status == FtsFinStatus.REJECT) {
            return "badge-reject";
        } else if (status == FtsFinStatus.APPLYING) {
            return "badge-exec-ing";
        } else if (status == FtsFinStatus.CFRM) {
            return "badge-draft";
        } else if (status == FtsFinStatus.CFRMING) {
            return "badge-confirm-asset-ing";
        }
        return "badge-finished";
    }
}

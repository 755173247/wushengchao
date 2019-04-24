package com.sdc.factor.entity.business.entity;

import com.sdc.factor.entity.common.annotations.ContractVar;
import com.sdc.factor.entity.common.utils.DATE;
import com.sdc.factor.entity.common.utils.NULL;
import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Function;

/**
 * 
 * 资产如：AP/AR
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "projId"), @Index(columnList = "finId"), @Index(columnList = "sha256") })
@ToString
@SuppressWarnings("serial")
public class FtsAsset extends BaseModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(FtsAsset.class);

    /** 筛选状态 */
    public enum FtsFilterStatus {
        /** 待筛选 */
        ING("待筛选"),
        /** 合格 */
        OK("合格"),
        /** 不合格 */
        NO("不合格");

        public final String title;

        private FtsFilterStatus(String title) {
            this.title = title;
        }
    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long assetId;

    @Column(nullable = false)
    private Long projId;

    /** 融资申请单 */
    @Column
    private Long finId;

    @ContractVar(name = "项目名称")
    /** 项目名称 */
    @Column(nullable = false, length = 256)
    private String projName;

    @ContractVar(name = "项目编号")
    /** 项目编号 */
    @Column(nullable = false, length = 32)
    private String projNum;

    /** 核心企业Id */
    @Column(nullable = false)
    private Long coreId;

    @ContractVar(name = "核心企业名称")
    /** 核心企业名称 */
    @Column(nullable = false, length = 256)
    private String coreName;

    /** 买方entId */
    @Column(nullable = false)
    private Long buyerId;

    @ContractVar(name = "买方名称")
    /** 买方entName */
    @Column(nullable = false)
    private String buyerName;

    /** 卖方entId */
    @Column(nullable = false)
    private Long sellerId;

    @ContractVar(name = "卖方名称")
    /** 卖方entName */
    @Column(nullable = false)
    private String sellerName;

    @ContractVar(name = "单据编号")
    /** 单据编号 */
    @Column(length = 256)
    private String billNum;

    @ContractVar(name = "债权金额")
    /** 实际金额/债权金额 */
    @Column(nullable = false, columnDefinition = "numeric(19,2) NOT NULL default 0")
    private BigDecimal amount = BigDecimal.ZERO;

    @ContractVar(name = "其他金额")
    /** 其他金额 */
    @Column(nullable = false, columnDefinition = "numeric(19,2) NOT NULL default 0")
    private BigDecimal extraAmt = BigDecimal.ZERO;

    /** 筛选状态 */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsFilterStatus filterStatus;

    @ContractVar(name = "到期日")
    /** 到期日 */
    @Column
    private Date dueDate;

    /** 模拟资产 */
    @Column(nullable = false, columnDefinition = "char(1) NOT NULL default 0")
    private Boolean mock;

    @ContractVar(name = "自债权人转让至保理日期")
    /** 自债权人转让至保理日期 */
    @Column
    private Date toFactorDate;

    @ContractVar(name = "自保理转让至信托的日期")
    /** 自保理转让至信托的日期 */
    @Column
    private Date toTurstDate;

    /** ----基础合同和发票扫描件，catiiFile附件表分组 */
    @Column(length = 32)
    private String tradeGroup;

    /** ----转入通知书纸质合同分组 */
    @Column(length = 32)
    private String txPaperGroup;

    /** ----付款确认函纸质合同分组 */
    @Column(length = 32)
    private String payPaperGroup;

    /** 通知书生成 */
    @Column
    private Boolean signStatus1;

    /** 付款确认函生成 */
    @Column
    private Boolean signStatus2;

    /** 是否存在转让通知书、放款确认函 T：通知书,P：确认函 ,值TP */
    @Column
    private String letters;

    @ContractVar(name = "扩展属性1")
    @Column(length = 256)
    private String attr0;

    @ContractVar(name = "扩展属性2")
    @Column(length = 256)
    private String attr1;

    @ContractVar(name = "扩展属性3")
    @Column(length = 256)
    private String attr2;

    @ContractVar(name = "扩展属性4")
    @Column(length = 256)
    private String attr3;

    @ContractVar(name = "扩展属性5")
    @Column(length = 256)
    private String attr4;

    @ContractVar(name = "扩展属性6")
    @Column(length = 256)
    private String attr5;

    @ContractVar(name = "扩展属性7")
    @Column(length = 256)
    private String attr6;

    @ContractVar(name = "扩展属性8")
    @Column(length = 256)
    private String attr7;

    @ContractVar(name = "扩展属性9")
    @Column(length = 256)
    private String attr8;

    @ContractVar(name = "扩展属性10")
    @Column(length = 256)
    private String attr9;

    @ContractVar(name = "扩展属性11")
    @Column(length = 256)
    private String attr10;

    @ContractVar(name = "扩展属性12")
    @Column(length = 256)
    private String attr11;

    @ContractVar(name = "扩展属性13")
    @Column(length = 256)
    private String attr12;

    @ContractVar(name = "扩展属性14")
    @Column(length = 256)
    private String attr13;

    @ContractVar(name = "扩展属性15")
    @Column(length = 256)
    private String attr14;

    /** 把资产中所有字段串联起来，计算sha256，确定资产唯一性 */
    @Column(length = 128)
    private String sha256;

    /** 是否上传附件 0:否,1:是 */
    @Column(nullable = false, columnDefinition = "char(1) NOT NULL default 0")
    private Boolean hasTradeAccry;

    /** 基础合同和发票扫描件、上传的附件数量 */
    @Column
    private Integer tradeCount = 0;

    /** 转入通知书纸质合同附件数量 */
    @Column
    private Integer txPaperCount = 0;

    /** ----付款确认函纸质合同分组数量 */
    @Column
    private Integer payPaperCount = 0;

    @ContractVar(name = "折价率/融资比列")
    /** 折价率/融资比列 */
    @Column
    private String discountRate;

    @ContractVar(name = "融资金额 ")
    /** 融资金额 */
    @Column
    private BigDecimal transAmt;

    /** 通知书、确认函状态查询使用 */
    @Transient
    private String status;

    /** 开始时间 :临时变量添加 @Transient，不属于数据库字段 */
    @Transient
    private Date startTime;

    /** 截止时间 */
    @Transient
    private Date endTime;

    /** 核心、链属查询使用 权限划分 */
    @Transient
    private Long condId;

    @Transient
    public String getFilterBadge() {
        if (filterStatus == FtsFilterStatus.ING) {
            return "badge-teal";
        } else if (filterStatus == FtsFilterStatus.NO) {
            return "badge-pmy";
        }
        return "badge-info";
    }

    @Transient
    public String getSignStatusCss() {
        if (NULL.nvl(this.signStatus1)) {
            return "badge-info";
        }
        return "badge-teal";
    }

    @Transient
    public String getSignStatusCss2() {
        if (NULL.nvl(this.signStatus2)) {
            return "badge-info";
        }
        return "badge-teal";
    }

    /**
     * 生成sha256信息摘要
     * 
     * @return
     */
    public String toSha256() {
        //单独处理时间
        String due = dueDate == null ? "" : DATE.clearTime(dueDate).getTime() + "";
        Function<String, String> fn = s -> NULL.nvl(s).trim();
        String s = buyerName + "||" + sellerName + "||" + amount + "||" + fn.apply(billNum) + "||" + NULL.nvl(extraAmt)
                + "||" + due + "||" + fn.apply(attr0) + "||" + fn.apply(attr1) + "||" + fn.apply(attr2) + "||"
                + fn.apply(attr3) + "||" + fn.apply(attr4) + "||" + fn.apply(attr5) + "||" + fn.apply(attr6) + "||"
                + fn.apply(attr7) + "||" + fn.apply(attr8) + "||" + fn.apply(attr9) + "||" + fn.apply(attr10) + "||"
                + fn.apply(attr11) + "||" + fn.apply(attr12) + "||" + fn.apply(attr13) + "||" + fn.apply(attr14);
        return DigestUtils.sha256Hex(s.toUpperCase());
    }

    public boolean assetIsEmpty() {
        Function<String, String> fn = s -> NULL.nvl(s).trim();
        String due = dueDate == null ? "" : DATE.clearTime(dueDate).getTime() + "";
        String amt = BigDecimal.ZERO.compareTo(NULL.nvl(amount)) == 0 ? "" : amount.toPlainString();
        String extAmt = BigDecimal.ZERO.compareTo(NULL.nvl(extraAmt)) == 0 ? "" : extraAmt.toPlainString();
        String s = buyerName + sellerName + amt + fn.apply(billNum) + extAmt + due + fn.apply(attr0) + fn.apply(attr1)
                + fn.apply(attr2) + fn.apply(attr3) + fn.apply(attr4) + fn.apply(attr5) + fn.apply(attr6)
                + fn.apply(attr7) + fn.apply(attr8) + fn.apply(attr9) + fn.apply(attr10) + fn.apply(attr11)
                + fn.apply(attr12) + fn.apply(attr13) + fn.apply(attr14);

        if (StringUtils.isBlank(s)) {
            return true;
        }
        return false;
    }

    /**
     * 获得扩展属性的值
     * @param index
     * @return
     */
    public String getAttr(int index) {
        Field field = ReflectionUtils.findField(FtsAsset.class, "attr" + index);
        if (field == null) {
            LOGGER.error("Fail to find the attribute with index " + index + " in class " + FtsAsset.class.getName());
            return null;
        } else {
            Object value = ReflectionUtils.getField(field, this);
            LOGGER.debug("Got value for attribute attr" + index + ": " + String.valueOf(value));
            return value == null ? null : String.valueOf(value);
        }
    }

    /**
     * 设置扩展属性的值
     * @param index
     * @param value
     */
    public void setAttr(int index, String value) {
        Field field = ReflectionUtils.findField(FtsAsset.class, "attr" + index);
        if (field == null) {
            LOGGER.error("Fail to find the attribute with index " + index + " in class " + FtsAsset.class.getName());
        } else {
            ReflectionUtils.setField(field, this, value);
        }
    }
}

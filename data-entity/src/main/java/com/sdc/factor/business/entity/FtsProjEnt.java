package com.sdc.factor.business.entity;

import com.sdc.factor.common.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

/**
 * 项目相关企业
 *
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "projId,entId", unique = true) })
public class FtsProjEnt extends BaseModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(FtsProjEnt.class);

    /**
     * 项目角色
     */
    public enum FtsProjRole {
        /** 保理 */
        FACTOR,
        /** 信托机构 */
        TRUST,
        /** 主承销商 */
        MAIN_UW,
        /** 核心企业 */
        CORE,
        /** 项目公司 */
        PRJ,
        /** 链属企业 */
        LINK;

        public static FtsProjRole instance(String s) {
            for (FtsProjRole ret : FtsProjRole.values()) {
                if (ret.name().equalsIgnoreCase(s)) {
                    return ret;
                }
            }
            return null;
        }

        /** 在指定项目中，只能有一条记录的企业角色 */
        public boolean isOnlyOne() {
            return this == CORE || this == FACTOR || this == TRUST || this == MAIN_UW;
        }
    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long projEntId;

    /** 项目Id */
    @Column(nullable = false)
    private Long projId;

    /** 项目名称 */
    @Column(nullable = false, length = 256)
    private String projName;

    /** 企业Id */
    @Column(nullable = false)
    private Long entId;

    /** 企业名称 */
    @Column(nullable = false, length = 256)
    private String entName;

    /** 折价率/融资比列 */
    @Column
    private String discountRate;

    /** 项目角色 */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsProjRole projRole;

    /** 短信授权随机码 */
    @Column
    private String cfcaCode;

    /** 授权时间 yyyyMMddHHmmss */
    @Column
    private String cfcaAuthTime;

    /** 授权ip地址 */
    @Column
    private String cfcaLocation;

    /** 授权状态 */
    @Column
    private Boolean cfcaAuthStatus;

    /** 联系人/管理员姓名 */
    @Transient
    private String adminName;

    /** 联系人/管理员电话 */
    @Transient
    private String adminMobile;

    /** 联系人/管理员邮箱 */
    @Transient
    private String adminMail;

    /** 联系人/管理员身份证号 */
    @Transient
    private String adminIdNo;

    /** 折价率显示值 */
    @Transient
    public String getRateStr() {
        String percentageNumber = "0";
        if (StringUtils.isNotBlank(this.discountRate)) {
            try {
                Double doubleValue = Double.valueOf(this.discountRate);
                percentageNumber = String.valueOf(doubleValue * 100);
            } catch (Exception e) {
                LOGGER.error("Fail to convert " + this.discountRate + " to double value", e);
            }
        }
        return percentageNumber + "%";
    }

    /** 角色类型查询 */
    @Transient
    private List<FtsProjRole> projRoleList;
}

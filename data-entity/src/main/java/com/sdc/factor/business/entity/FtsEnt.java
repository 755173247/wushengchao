package com.sdc.factor.business.entity;

import com.sdc.factor.common.annotations.ContractVar;
import com.sdc.factor.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 企业信息
 *
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "entName") })
@ToString
public class FtsEnt extends BaseModel {

    /** 企业类型 */
    public enum FtsEntType {
        /** 保理公司 */
        FACTOR(0),
        /** 信托机构 */
        TRUST(1),
        /** 主承销商 */
        MAIN_UW(2),
        /** 核心 */
        CORE(3),
        /** 一般企业 */
        COMP(4);

        public static FtsEntType instance(String s) {
            for (FtsEntType ret : FtsEntType.values()) {
                if (ret.name().equalsIgnoreCase(s)) {
                    return ret;
                }
            }
            return null;
        }

        private FtsEntType(int level) {
            this.level = level;
        }

        /** 如果在邀约树状结构中，不同企业类型的节点层级 */
        public final int level;
    }

    /** 签约状态 */
    public enum FtsSignStatus {
        /** 未邀请 */
        UNINVITE,
        /** 已邀请 */
        INVITED,
        /** 已上线 */
        ONLINE,
        /** 已认证 */
        AUTH
    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long entId;

    @ContractVar(name = "企业名称")
    /** 企业名称 */
    @Column(nullable = false, length = 256)
    private String entName;

    /** 企业类型 */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsEntType entType;

    @ContractVar(name = "统一社会信用代码")
    /** 统一社会信用代码 */
    @Column(length = 32)
    private String creditCode;

    /** 签约状态 */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsSignStatus signStatus;

    /** 邀请时间 */
    @Column
    private Date inviteTime;

    @ContractVar(name = "法人代表姓名")
    /** 法人代表姓名 */
    @Column(length = 128)
    private String legalName;

    /** 固定电话 */
    @Column(length = 32)
    private String fixPhone;

    @ContractVar(name = "公司注册地址")
    /** 公司注册地址 */
    @Column(length = 1024)
    private String regAddr;

    @ContractVar(name = "办公地址/经营地址")
    /** 办公地址/经营地址 */
    @Column(length = 1024)
    private String officeAddr;

    /** 公司介绍 */
    @Column
    @Lob
    private String entDesc;

    @ContractVar(name = "银行户名")
    /** 银行户名 */
    @Column(length = 128)
    private String bankAccName;

    @ContractVar(name = "银行账号")
    /** 银行账号 */
    @Column(length = 128)
    private String bankAccount;

    @ContractVar(name = "开户行")
    /** 开户行 */
    @Column(length = 128)
    private String bankName;

    /** 营业执照附件分组 , 关联CatiiFile.fileGroup */
    @Column(length = 32)
    private String bizLicAccy;

    /** 贷款卡附件分组 , 关联CatiiFile.fileGroup */
    @Column(length = 32)
    private String loanCardAccy;

    /** 法人身份证附件分组 , 关联CatiiFile.fileGroup */
    @Column(length = 32)
    private String legalIdNoAccy;

    /** 公司章程附件分组 , 关联CatiiFile.fileGroup */
    @Column(length = 32)
    private String rulesAccy;

    /** 财务报表附件分组 , 关联CatiiFile.fileGroup */
    @Column(length = 32)
    private String coaAccy;

    /** 审计报告附件分组 , 关联CatiiFile.fileGroup */
    @Column(length = 32)
    private String auditRptAccy;

    /** 被授权人身份证附件分组 */
    @Column(length = 32)
    private String adminIdNoAccy;

    /** 授权书附件分组 */
    @Column(length = 32)
    private String proxyAccy;

    /** 其他附件分组 , 关联CatiiFile.fileGroup */
    @Column(length = 32)
    private String otherAccy;

    /** 被授权人Id */
    @Column
    private Long adminId;

    @ContractVar(name = "传真")
    /** 传真 */
    @Column
    private Long fax;

    /** 印章存放路径（*.png），格式：ymd/group/uuid.扩展名 */
    @Column(length = 2048)
    private String sealPath;

    /** CFCA印章Id */
    @Column(length = 32)
    private String cfcaSealId;

    /** CFCA用户Id */
    @Column(length = 32)
    private String cfcaUserId;

    /** CFCA用户手机号码 */
    @Column
    private String cfcaUserMobile;

    /** CFCA用户签约人 */
    @Column
    private String cfcaUserName;

    /** 开始时间 : 不属于数据库字段 */
    @Transient
    private Date startTime;

    /** 截止时间 */
    @Transient
    private Date endTime;

    @Transient
    private String entTypeEnums;

    @ContractVar(name = "被授权人")
    /** 被授权人/管理员姓名 */
    @Transient
    private String adminName;

    @ContractVar(name = "被授权人电话")
    /** 被授权人/管理员电话 */
    @Transient
    private String adminMobile;

    @ContractVar(name = "被授权人邮箱")
    /** 被授权人/管理员邮箱 */
    @Transient
    private String adminMail;

    @ContractVar(name = "被授权人身份证号")
    /** 被授权人/管理员身份证号 */
    @Transient
    private String adminIdNo;

    /** 企业联系人 */
    @Column(name = "ContactsName")
    private String contactsName;

    /** 联系人电话 */
    @Column(name = "ContactsMobie")
    private String contactsMobie;

    /** 联系人邮箱 */
    @Column(name = "ContactsMail")
    private String contactsMail;

    /** 企业类型查询 */
    @Transient
    private List<FtsEntType> typeList;

    /** 项目Id，仅企业信息查询 */
    @Transient
    private Long projId;

    /**
     * 被邀约的子级企业
     */
    @Transient
    public final List<FtsEnt> invChildren = new ArrayList<>();

    /**
     * 在邀约树形结构中，寻找某种类型的企业
     */
    public List<FtsEnt> listInvByType(FtsEntType type) {
        List<FtsEnt> ret = new ArrayList<>();
        if (entType == type) {
            ret.add(this);
        }
        listChildByType(ret, type);
        ret.addAll(invChildren.stream().filter(s -> s.entType == type).collect(Collectors.toList()));
        return ret;
    }

    private void listChildByType(List<FtsEnt> toList, FtsEntType type) {
        toList.addAll(invChildren.stream().filter(s -> s.entType == type).collect(Collectors.toList()));
        invChildren.forEach(s -> s.listChildByType(toList, type));
    }

    /**
     * 判断状态信息的颜色(根据枚举类型进行验证)
     */
    public String getSignBadge() {
        if (signStatus == FtsSignStatus.UNINVITE) {
            return "badge-teal";
        } else if (signStatus == FtsSignStatus.INVITED) {
            return "badge-pmy";
        } else if (signStatus == FtsSignStatus.ONLINE) {
            return "badge-draft";
        } else {
            return "badge-info";
        }
    }

    public void updateAdminInfo(FtsUser user) {
        if (user != null) {
            setAdminIdNo(user.getIdNo());
            setAdminMail(user.getMail());
            setAdminMobile(user.getMobile());
            setAdminName(user.getUserName());
        }
    }
}

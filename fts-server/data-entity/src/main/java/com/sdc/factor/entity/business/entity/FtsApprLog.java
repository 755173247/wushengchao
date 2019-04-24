package com.sdc.factor.entity.business.entity;


import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 
 * 项目/其他 审核记录
 * 
 * @author Sean
 * @since 2019-03-24
 *
 */
@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table
@ToString
public class FtsApprLog extends BaseModel {

    /** 通用审批动作 */
    public enum FtsApprAction {
        /** 同意 */
        AGREE,
        /** 拒绝 */
        REJECT;
    }

    /** 审批类型 */
    public enum FtsApprType {
        /** 项目 */
        PROJECT,
        /** 附件 */
        ACCESS,
        /** 融资申请 */
        FIN;

        public static FtsApprType of(String s) {
            s = s == null ? null : s.trim();
            for (FtsApprType ret : FtsApprType.values()) {
                if (ret.name().equalsIgnoreCase(s)) {
                    return ret;
                }
            }
            return null;
        }
    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long logId;

    /** 关联单据Id */
    @Column(nullable = false)
    private Long srcBillId;

    /** 关联单据编号 */
    @Column(nullable = false, length = 32)
    private String srcBillNum;

    /** 审核单据类型 */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsApprType apprType;

    /** 操作人 ID */
    @Column(nullable = false)
    private Long operId;

    /** 操作人 名称 */
    @Column(nullable = false, length = 128)
    private String operName;

    /** 审核状态 */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsApprAction apprAction;

    /** 审批意见 */
    @Column
    @Lob
    private String opinion;
    
    @Transient
    private String apprActionStr;

    public FtsApprLog() {}

    public FtsApprLog(FtsApprAction action, String opinion) {
        this.apprAction = action;
        this.opinion = opinion;
    }
}

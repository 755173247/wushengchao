package com.sdc.factor.business.entity;


import com.sdc.factor.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 项目合同，包含签约主体
 *
 * @author Sean
 * @since 2019-03-24
 */
@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "contractNum", unique = true) })
@ToString
public class FtsContract extends BaseModel {

    /** 合同签署状态 */
    public enum FtsContractStatus {
        /** 未签订 */
        NO,
        /** 签署中，多方签约，有一方已经签署，但还有未签署 */
        ING,
        /** 已签订 */
        OK,
    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long contractId;

    /** 合同编号 */
    @Column(length = 256, nullable = false)
    private String contractNum;

    /** 关联产品协议Id */
    @Column(nullable = false)
    private Long protId;

    /** 项目Id */
    @Column(nullable = false)
    private Long projId;

    /** 协议名称 */
    @Column(nullable = false, length = 256)
    private String protName;

    /** 纸质合同附件分组 */
    @Column(length = 32)
    private String paperGroup;

    /** CFCA合同地址 */
    @Column(length = 2048)
    private String contractUrl;

    /** 签署方A,Id；按枚举类型排序,用英文逗号分隔 */
    @Column
    private Long signAId;

    /** 签署方B,Id；按枚举类型排序,用英文逗号分隔 */
    @Column
    private Long signBId;

    /** 签署方Name；用英文逗号分隔 */
    @Column
    @Lob
    private String signNames;

    /** 合同内容 */
    @Column
    @Lob
    private String content;

    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsContractStatus status;

    /** cfca合同编号 */
    @Column(length = 30)
    private String cfcaContractNo;

    /** PDF文件路径 */
    @Column(length = 2048)
    private String pdfPath;

    /** 协议类型 */
    @Column(length = 32, columnDefinition = "varchar(32) NOT NULL default 'COMMON'")
    @Enumerated(EnumType.STRING)
    private FtsProdProt.FtsProtType protType;

    /** 协议确认,可以签署 */
    @Column(columnDefinition = "char(1) default '0'")
    private Boolean agreeSign;

    /** 是否根节点 */
    @Transient
    private Boolean rootNode = false;

    public String getSignStatusCss() {
        if (this.status == FtsContractStatus.OK) {
            return "badge-info";
        } else if (this.status == FtsContractStatus.ING) {
            return "badge-provide-asset-ing";
        }
        return "badge-teal";
    }

    public FtsContract() {}

    public FtsContract(Long projId, Long protId, String protName, Boolean rootNode) {
        this.projId = projId;
        this.protId = protId;
        this.protName = protName;
        this.rootNode = rootNode;
    }
}

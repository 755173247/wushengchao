package com.sdc.factor.business.entity;

import com.sdc.factor.common.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 转让通知书、付款确认函
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "assetId, projId") })
@ToString
public class FtsLetter extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long letterId;

    /** 融资申请单编号 */
    @Column(nullable = false, length = 32)
    private String letterNum;

    /** 融资申请单据Id */
    @Column(nullable = false)
    private Long finId;

    /** 项目Id */
    @Column(nullable = false)
    private Long projId;

    /** 协议Id */
    @Column(nullable = false)
    private Long protId;

    /** 协议名称 */
    @Column(length = 1024)
    private String protName;

    /** 关联资产Id */
    @Column
    private Long assetId;

    /** 类型 */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsProdProt.FtsProtType letterType;

    /** 关联保理单据编号 */
    @Column(length = 32)
    private Long srcContractId;

    /** 签署方；存储FtsEntType的值，多个用英文逗号分隔 */
    @Column
    @Lob
    private String signs;

    /** 合同内容 */
    @Column
    @Lob
    private String content;

    /** cfca合同编号 */
    @Column(length = 30)
    private String cfcaContractNo;

    /** PDF文件路径 */
    @Column(length = 2048)
    private String pdfPath;

    /** 签订状态 */
    @Column(length = 32, columnDefinition = "varchar(32) NOT NULL default 'NO'")
    @Enumerated(EnumType.STRING)
    private FtsContract.FtsContractStatus status;

    /** 确认企业Id */
    @Column
    private Long confirmEntId;

    /** 确认企业 */
    @Column(length = 256)
    private String confirmEntName;

    /** 删除标记 */
    @Transient
    private boolean deleted;

    /** 保理显示转让通知书,付款确认函 */
    @Transient
    private boolean showTrans;

    @Transient
    FtsProdProt prot;

    @Transient
    private FtsAsset asset;

    @Transient
    public String getSignStatusCss() {
        if (this.status == FtsContract.FtsContractStatus.OK) {
            return "badge-info";
        }
        return "badge-teal";
    }

    public FtsLetter() {}

    public FtsLetter(Long assetId, FtsProdProt.FtsProtType protType) {
        this.assetId = assetId;
        this.letterType = protType;
    }
}

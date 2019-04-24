package com.sdc.factor.entity.business.entity;

import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * 协议体系库
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
public class FtsProdProt extends BaseModel {

    /**
     * 协议类型
     */
    public enum FtsProtType {
        /** 通用协议 */
        COMMON,
        /** 保理合同 */
        FACTOR,
        /** 转让通知书 */
        TRANS,
        /** 付款确认函 */
        PAY_OK,
    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long protId;

    /** 关联产品Id */
    @Column(nullable = false)
    private Long prodId;

    /** 合同编号前缀 */
    @Column(nullable = false, length = 32)
    private String numPrefix;

    /** 协议名称 */
    @Column(nullable = false, length = 256)
    private String protName;

    /** 必须上传 */
    @Column(nullable = false)
    private Boolean required;

    /** 附件路径 */
    @Column(length = 2048)
    private String filePath;

    /** 附件名称 */
    @Column(length = 256)
    private String fileName;

    /** 签署方；存储FtsEntType的值，多个用英文逗号分隔 */
    @Column
    @Lob
    private String signs;

    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsProtType protType;

    /** 顺序 */
    @Column(nullable = false)
    private Integer ord = 0;

    /** 模板内容 */
    @Column
    @Lob
    private String content;

    /** 与转入通知书、付款确认函生成有关 */
    @Column(nullable = false, columnDefinition = "char(1) NOT NULL default 0")
    private Boolean caseTag;

    /** 父级Id,保理协议使用,转入通知书、付款确认函 绑定前置合同 */
    @Column(columnDefinition = "int8 NOT NULL default 0")
    private Long parentId;

    @Transient
    private List<FtsContract> contractList;
}

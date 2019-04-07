package com.sdc.factor.business.entity;

import com.sdc.factor.common.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 协议 签署方、签章信息
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
public class FtsSignAttr extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long signAttrId;

    /** 关联协议Id */
    @Column(nullable = false)
    private Long protId;

    /** 项目角色 */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsProjEnt.FtsProjRole projRole;

    /** 印章签署关键字 */
    @Column(length = 64)
    private String keyword;

    /** X轴偏移坐标 */
    @Column(columnDefinition = "int4 DEFAULT 0")
    private Integer offsetCoordX;

    /** Y轴偏移坐标 */
    @Column(columnDefinition = "int4 DEFAULT 0")
    private Integer offsetCoordY;

    /** 签章图片宽度 */
    @Column(columnDefinition = "int4 DEFAULT 0")
    private Integer imageWidth;

    /** 签章图片高度 */
    @Column(columnDefinition = "int4 DEFAULT 0")
    private Integer imageHeight;

    /** 是否签章 */
    @Column(nullable = false)
    private Boolean sign;

    /** 签章顺序 */
    @Column(columnDefinition = "int4 DEFAULT 0")
    private Integer signOrd;
}

package com.sdc.factor.entity.business.entity;

import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

/**
 * 报表查询条件
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "infoId") })
@ToString
public class RptCond extends BaseModel {

    /**
     * 查询条件类型
     */
    public enum RptCondType {
        /** 日期 */
        DATE,
        /** 字符串 */
        STR
    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long condId;

    @Column(nullable = false)
    private Long infoId;

    /** 条件代码 */
    @Column(nullable = false, length = 128)
    private String condCode;

    /** 字段名称 */
    @Column(nullable = false, length = 128)
    private String condName;

    /** 查询条件类型 */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RptCondType condType = RptCondType.STR;

    /** 顺序 */
    @Column(nullable = false)
    private Integer condOrd;

    public boolean codeEquals(RptCond that) {
        return that != null && StringUtils.isNotEmpty(this.condCode) && StringUtils.isNotEmpty(that.condCode) && this.condCode.equals(that.condCode);
    }
}

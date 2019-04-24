package com.sdc.factor.entity.business.entity;

import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

/**
 * 报表字段
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
public class RptField extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long fieldId;

    @Column(nullable = false)
    private Long infoId;

    /** 字段代号 */
    @Column(nullable = false, length = 128)
    private String fieldCode;

    /** 字段名称 */
    @Column(nullable = false, length = 128)
    private String fieldName;

    /** 顺序 */
    @Column(nullable = false)
    private Integer fieldOrd;

    /** 对应ResultSet中的字段索引，从1开始 */
    @Transient
    private int icol;

    public boolean codeEquals(RptField that) {
        return that != null && StringUtils.isEmpty(this.fieldCode) && StringUtils.isNotEmpty(that.fieldCode) && this.fieldCode.equals(that.fieldCode);
    }
}

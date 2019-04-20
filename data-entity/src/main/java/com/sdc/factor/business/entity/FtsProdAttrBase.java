package com.sdc.factor.business.entity;

import com.sdc.factor.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * 产品或项目要素（属性）基类
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@MappedSuperclass
@ToString
public class FtsProdAttrBase extends BaseModel {

    /** 要素类型 */
    public enum FtsAttrType {
        /** 文本 */
        TEXT,
        /** 单选 */
        SINGLE,
        /** 整数 */
        INTEGER,
        /** 小数 */
        DECIMAL,
        /** 百分比 */
        PERCENT,
        /** 日期 */
        DATE
    }

    /** 要素名称 */
    @Column(nullable = false, length = 256)
    private String attrName;

    /** 关联产品Id */
    @Column(nullable = false)
    private Long prodId;

    /** 产品名称 */
    @Column(nullable = false, length = 256)
    private String prodName;

    /** 属性分组 */
    @Column(nullable = false, length = 256)
    private String attrGroup;

    /** 要素类型 */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsAttrType attrType;

    /** 顺序 */
    @Column(nullable = false)
    private Integer ord = 0;

    /** 选项，以|风格 */
    @Column
    @Lob
    private String options;

    @Transient
    private List<String> listOptions;

    public List<String> getListOptions() {
        return Stream.of(StringUtils.split(this.options, "|")).filter(StringUtils::isNotBlank).map(String::trim).collect(Collectors.toList());
    }
}

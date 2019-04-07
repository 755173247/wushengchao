package com.sdc.factor.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 数据模型基类,包含通用字段
 * 
 * @author Sean
 * @since 2019-03-24
 */
@MappedSuperclass
@Getter
@Setter
@Accessors(chain = true)
public class BaseModel implements java.io.Serializable {

    /** 创建时间 */
    @Column(nullable = false)
    private Date createTime;

    /** 创建人 */
    @Column(nullable = false)
    private Long createBy;

    /** 更新时间 */
    @Column(nullable = false)
    private Date updateTime;

    /** 更新人 */
    @Column(nullable = false)
    private Long updateBy;

    /** 启用? */
    @Column(nullable = false)
    private Boolean enabled;

    /** 根据具体业务场景，放置临时信息到该变量中 */
    @Transient
    private String str;

    /** 多个字段like查询专用 */
    @Transient
    private String likeText;

    /**
     * 清除所有基础属性
     */
    public void clearBaseValues() {
        createBy = null;
        createTime = null;
        updateBy = null;
        updateTime = null;
        enabled = null;
    }

    /**
     * 在保存实体之前，完成对通用字段值的设置操作
     */
    @PreUpdate
    public void beforeSave() {
        Date now = new Date();
        if (createTime == null) {
            createTime = now;
        }
        //TODO:: get user information through spring security framework methods
        //ctx.getAccountId();
        long userId = 1;
        updateTime = now;
        if (createBy == null) {
            createBy = userId;
        }
        updateBy = userId;
        if (enabled == null) {
            enabled = true;
        }
    }
}
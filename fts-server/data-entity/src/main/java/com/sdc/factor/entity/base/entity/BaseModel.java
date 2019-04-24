package com.sdc.factor.entity.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdc.factor.entity.business.entity.FtsUser;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;

/**
 * 数据模型基类,包含通用字段
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@MappedSuperclass
public class BaseModel implements java.io.Serializable {

    /**
     * 通过BeanUtils拷贝时忽略的属性
     */
    public static String[] COPY_IGNORE_PROPERTIES = {
            "createTime",
            "updateTime",
            "createBy",
            "updateBy",
            "enabled"
    };

    /** 创建时间 */
    @Column(nullable = false)
    @JsonIgnore
    private Date createTime;

    /** 创建人 */
    @Column
    @JsonIgnore
    private Long createBy;

    /** 更新时间 */
    @Column(nullable = false)
    @JsonIgnore
    private Date updateTime;

    /** 更新人 */
    @Column
    @JsonIgnore
    private Long updateBy;

    /** 启用? */
    @Column(nullable = false)
    private Boolean enabled;

    @JsonIgnore
    @Column(name = "version", length = 20, nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    @Version
    private Long version;

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
        updateTime = now;
        if (enabled == null) {
            enabled = true;
        }
        //设置当前更新的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof FtsUser) {
                Long userId = ((FtsUser) principal).getUserId();
                if (createBy == null) {
                    createBy = userId;
                }
                updateBy = userId;
            }
        }
    }
}
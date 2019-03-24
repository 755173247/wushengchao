package com.sdc.factor.business.entity;

import com.sdc.factor.common.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户信息
 *
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "mobile", unique = true), @Index(columnList = "mail", unique = true),
        @Index(columnList = "invCode", unique = true) })
public class FtsUser extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long userId;

    /** 用户姓名 */
    @Column(nullable = false, length = 128)
    private String userName;

    /** 手机 */
    @Column(nullable = false, length = 32)
    private String mobile;

    /** 邮箱 */
    @Column(length = 256)
    private String mail;

    /** 身份证号 */
    @Column(length = 32)
    private String idNo;

    /** 角色Id */
    @Column(nullable = false)
    private Long roleId;

    /** 企业Id */
    @Column(nullable = false)
    private Long entId;

    /** 企业名称 */
    @Column(nullable = false, length = 256)
    private String entName;

    /** 密码 */
    @Column(nullable = false, length = 64)
    private String userPswd;

    /** 邀请码，不能重复 */
    @Column(length = 32)
    private String invCode;

    /** 邀约码失效毫秒数，如 30分钟 System.currentMillis() + 1000 * 60 * 30 */
    @Column
    private Long invExpired;

    /** 随机码，通过短信发送给用户 */
    @Column(length = 32)
    private String randCode;

    /** 随机码失效毫秒数，如 30分钟 System.currentMillis() + 1000 * 60 * 30 */
    @Column
    private Long randExpired;

    /** 是否为超级管理员 */
    @Column(nullable = false)
    private Boolean superUser = false;

    /** 密码找回临时令牌 */
    @Column(length = 64)
    private String tempToken;

    /** 密码找回临时令牌，失效时间 */
    @Column()
    private Date tempExpired;

    @Transient
    private String newPswd;

    @Transient
    private String repPswd;
}

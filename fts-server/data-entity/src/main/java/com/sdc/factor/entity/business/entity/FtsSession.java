package com.sdc.factor.entity.business.entity;

import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 会话
 *
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "sessionUuid", unique = true) })
@ToString
public class FtsSession extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long sessionId;

    @Column()
    private String sessionUuid;

    /** 帐号ID */
    @Column(nullable = false)
    private Long userId;

    /** 帐号名称 */
    @Column(nullable = false, length = 128)
    private String userName;

    /** 角色ID */
    @Column(nullable = false)
    private Long roleId;

    /** 企业Id */
    @Column
    private Long entId;

    /** 企业名称 */
    @Column(length = 128)
    private String entName;

    /** 是否为超级管理员 */
    @Column(nullable = false)
    private Boolean superUser;

    /** 用户当前登录IP地址 */
    @Column
    private String ipAddr;

    //TODO: convert session data to request context
    /*
    public void toCtx(Ctx ctx) {
        ctx.setAccountId(getUserId());
        ctx.setAccountName(getUserName());
        ctx.setSession(getSessionUuid());
        ctx.setOrgId(getEntId());
        ctx.setOrgName(getEntName());
        ctx.setSuperUser(superUser);
        ctx.getExt().put("roleId", STR.toString(getRoleId().toString()));
        ctx.setIpAddr(getIpAddr());
    }
    */
}

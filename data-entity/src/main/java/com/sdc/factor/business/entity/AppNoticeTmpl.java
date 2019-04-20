package com.sdc.factor.business.entity;


import com.sdc.factor.common.beans.KV;
import com.sdc.factor.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息模版
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table
@ToString
public class AppNoticeTmpl extends BaseModel {

    /** 模版类型、应用场景 */
    public enum AppNoticeTmplType {
        /** 邀约 */
        INVITE {

            @Override
            public void initVariables(List<KV> varList) {
                varList.add(new KV("srcEnt", "发起企业"));
                varList.add(new KV("dstEnt", "被邀请企业"));
                varList.add(new KV("href", "注册链接"));
                varList.add(new KV("invCode", "邀请码"));
            }
        },
        /** 登录随机码 */
        LOGIN_RAND_CODE {

            @Override
            public void initVariables(List<KV> varList) {
                varList.add(new KV("randCode", "短信验证码"));
                varList.add(new KV("userName", "用户名称"));
            }
        },
        /** 密码找回 */
        FORGET_PSWD {

            @Override
            public void initVariables(List<KV> varList) {
                varList.add(new KV("plmName", "平台名称"));
                varList.add(new KV("userName", "发起用户"));
                varList.add(new KV("href", "密码找回链接"));
            }
        };

        /**
         * 每种业务场景需要对应的变量
         */
        public List<KV> variables() {
            List<KV> ret = new ArrayList<KV>();
            initVariables(ret);
            return ret;
        }

        /**
         * 每种业务场景需要提供变量
         */
        public abstract void initVariables(List<KV> varList);

        public static AppNoticeTmplType inst(String s) {
            s = StringUtils.trim(s);
            for (AppNoticeTmplType ret : AppNoticeTmplType.values()) {
                if (ret.name().equalsIgnoreCase(s)) {
                    return ret;
                }
            }
            return null;
        }
    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long tmplId;

    /** 模版名称，邮件时，作为邮件标题 */
    @Column(nullable = false, length = 256)
    private String tmplName;

    /** 消息类型Email、SMS */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppNotice.AppNoticeType noticeType;

    /** 模版类型使用场景 */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppNoticeTmplType tmplType;

    /** 模版内容 */
    @Column
    @Lob
    private String tmplContent;
}

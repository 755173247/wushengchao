package com.sdc.factor.business.entity;


import com.sdc.factor.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统消息表
 *
 * @author WJ
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table
@ToString
public class AppNotice extends BaseModel {

    /** 消息类型 */
    public enum AppNoticeType {
        /**
         * 邮件通知
         */
        EMAIL,
        /**
         * 短信通知
         */
        SMS
    }

    /** 消息状态 */
    public enum AppNoticeStatus {
        /** 待发送 */
        SENDING,
        /** 待重试 */
        RETRING,
        /** 超时 */
        TIMEOUT,
        /** 发送成功 */
        SUCCESS
    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long noticeId;

    /** 发送目标如：邮箱地址、手机号 */
    @Column(nullable = false)
    private String targetAddr;

    /** 标题 */
    @Column
    private String title;

    /** 正文 */
    @Column
    @Lob
    private String content;

    /** 消息类型(Email) */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppNoticeType noticeType;

    /** 待发送时间 */
    @Column(nullable = false)
    private Date sendTime;

    /** 超时时间 */
    @Column(nullable = false)
    private Date expiredTime;

    /** 状态(待发送 sending，待重试 retring，超时timeout，发送成功success) */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppNoticeStatus noticeStatus;

    /** 消息来源，由业务功能提供 */
    @Column()
    private String sourceDesc;

    /** 轮询次数，每发送一次加1 */
    @Column(nullable = false)
    private Long pollCount;

    /** 日志信息 */
    @Column()
    private String logger;
}

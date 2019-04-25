package com.sdc.factor.business.entity;


import com.sdc.factor.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 待发系统消息表(WAIT SENDING)，具体内容存放在系统消息表
 *
 * @author Sean
 * @since 2019-03-24
 */
@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table
@ToString
public class AppNoticeWs extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long noticeWsId;

    /** 消息ID */
    @Column(nullable = false)
    private Long noticeId;

    /** 消息类型(Email) */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppNotice.AppNoticeType noticeType;

    /** 待发送时间 */
    @Column(nullable = false)
    private Date sendTime;

    /** 超时时间 */
    @Column(nullable = false)
    private Date expiredTime;

    /** 轮询次数，每发送一次加1 */
    @Column(nullable = false)
    private Long pollCount;

    /** 非DB字段，待发消息对应的消息实体 */
    @Transient
    private AppNotice notice;
}

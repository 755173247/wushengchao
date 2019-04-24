package com.sdc.factor.entity.business.entity;

import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 我的企业/我邀请的企业信息
 *
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "entPid,entId", unique = true) })
@ToString
public class FtsMyEnt extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long myEntId;

    /** 主企业Id */
    @Column(nullable = false)
    private Long entPid;

    /** 从企业Id */
    @Column(nullable = false)
    private Long entId;

    /** 从企业名称 */
    @Column(nullable = false, length = 256)
    private String entName;
}

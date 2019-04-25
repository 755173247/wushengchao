package com.sdc.factor.funder.entity;

import com.sdc.factor.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 第三方系统信息存储表
 *
 * @author Sean
 * @since 2019-04-20
 */

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "thirdparty_system", uniqueConstraints = {
        @UniqueConstraint(name = "uq_3rd_system_id", columnNames = {"app_id"})
})
@ToString
public class ThirdpartySystem extends BaseModel {

    /**
     * 第三方系统的id
     */
    @Id
    @GeneratedValue(generator = "hilo")
    private Long id;

    /**
     * 第三方系统的名称
     */
    @Column(name = "name", nullable = true)
    private String name;

    /**
     * 第三方系统负责人的电话号码
     */
    @Column(name = "contact_phone", nullable = true)
    private String contactPhone;

    /**
     * 第三方系统负责人的姓名
     */
    @Column(name = "contact", nullable = true)
    private String contact;

    /**
     * 由我方分配给第三方系统的唯一id
     */
    @Column(name = "app_id", nullable = false, length = 64)
    private String appId;

    /**
     * 由我方分配给第三方系统的加密密钥（不可外传）
     */
    @Column(name = "app_secret", nullable = false, length = 128)
    private String appSecret;
}

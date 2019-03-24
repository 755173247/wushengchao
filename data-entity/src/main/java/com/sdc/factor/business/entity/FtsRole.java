package com.sdc.factor.business.entity;

import com.sdc.factor.common.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * 角色
 *
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table
public class FtsRole extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long roleId;

    /** 角色名称 */
    @Column(nullable = false, length = 128)
    private String roleName;

    /** 角色对应、企业类型 */
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private FtsEnt.FtsEntType entType;

    /** 菜单编号以逗号分割连接字符串 */
    @Column
    @Lob
    private String menus;

    @Transient
    private List<FtsUser> accList;
}

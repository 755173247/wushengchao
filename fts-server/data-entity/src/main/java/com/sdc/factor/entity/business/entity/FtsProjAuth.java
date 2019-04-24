package com.sdc.factor.entity.business.entity;

import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 项目授权表
 *
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "userId,projId", unique = true) })
@ToString
public class FtsProjAuth extends BaseModel {

    /** 授权类型 */
    public enum FtsProjAuthType {
        /** 项目总监 */
        DIR,
        /** 项目经理 */
        MGT,
        /** 项目成员 */
        MEM;

        public static FtsProjAuthType instance(String s) {
            for (FtsProjAuthType ret : FtsProjAuthType.values()) {
                if (ret.name().equalsIgnoreCase(s)) {
                    return ret;
                }
            }
            return null;
        }

    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long projAuthId;

    /** 项目Id */
    @Column(nullable = false)
    private Long projId;

    /** 项目名称 */
    @Column(nullable = false, length = 256)
    private String projName;

    /** 人员Id */
    @Column(nullable = false)
    private Long userId;

    /** 人员名称 */
    @Column(nullable = false, length = 256)
    private String userName;

    /** 项目权限类型 */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private FtsProjAuthType authType;

    /** 权限说明 */
    @Transient
    private String authDesc;

    public String getAuthDesc() {
        if (this.authType == FtsProjAuthType.DIR) {
            this.authDesc = "审核、查看";
        } else if (this.authType == FtsProjAuthType.MGT) {
            this.authDesc = "修改、查看";
        } else {
            this.authDesc = "查看";
        }
        return authDesc;
    }
}

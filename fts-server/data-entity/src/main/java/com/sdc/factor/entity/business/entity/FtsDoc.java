package com.sdc.factor.entity.business.entity;


import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 文本信息
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
public class FtsDoc extends BaseModel {

    /**
     * 文档类型
     * 
     * @author catii
     * 
     */
    public enum FtsDocType {
        /** 注册协议 */
        REG_AGREEMENT,
        /** CFCA平台协议 */
        SVR_AGREEMENT,
        /** CFCA协议 */
        CFCA_AGREEMENT;

        public static FtsDocType instance(String s) {
            s = s == null ? "" : s.trim();
            for (FtsDocType ret : FtsDocType.values()) {
                if (ret.name().equalsIgnoreCase(s)) {
                    return ret;
                }
            }
            return null;
        }

    }

    @Id
    @GeneratedValue(generator = "hilo")
    private Long docId;

    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private FtsDocType docType;

    @Column
    @Lob
    private String doc;
}

package com.sdc.factor.entity.workflow.entity;

import com.sdc.factor.entity.common.beans.KV;
import com.sdc.factor.entity.common.utils.JsonUtils;
import com.sdc.factor.entity.common.workflow.WfrMiscTypes;
import com.sdc.factor.entity.base.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;
import java.util.function.Predicate;

/**
 * 工作流流程节点&流程状态
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(indexes = { @Index(columnList = "statusCode,flowType", unique = true), @Index(columnList = "flowType") })
@ToString
public class WfrStatus extends BaseModel {

    @Id
    @GeneratedValue(generator = "hilo")
    private Long statusId;

    /** 节点&状态名称 */
    @Column(nullable = false, length = 32)
    private String statusCode;

    /** 节点&状态名称 */
    @Column(nullable = false, length = 128)
    private String statusName;

    /** 审批流类型 */
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private WfrMiscTypes.WfrFlowType flowType;

    /** 持有人JSON数组, eg：[{k:1, v:'张三'}] */
    @Lob
    @Column
    private String holders;

    /** 动作JSON数组，实际类型WfrAction */
    @Lob
    @Column
    private String actions;

    /** 顺序 */
    @Column(nullable = false)
    private Integer ord = 0;

    /** 是否提醒 */
    @Column(nullable = false)
    private Boolean alertable = true;

    /** 是否为终结状态 */
    @Column(nullable = false)
    private Boolean closed = false;

    /** 状态标示 */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private WfrMiscTypes.WfrStatusTag statusTag = WfrMiscTypes.WfrStatusTag.NONE;

    public static Predicate<WfrStatus> fnSameCode(String statusCode) {
        return status -> StringUtils.isNotEmpty(statusCode) && StringUtils.isNotEmpty(status.statusCode) && statusCode.equals(status.statusCode) ;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WfrStatus) {
            WfrStatus that = (WfrStatus) obj;
            return this.getStatusId().equals(that.getStatusId());
        }
        return false;
    }

    public List<KV> listHolders() {
        return JsonUtils.parseJsonArray(holders, KV.class);
    }
}

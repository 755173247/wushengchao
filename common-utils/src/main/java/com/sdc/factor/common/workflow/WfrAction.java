package com.sdc.factor.common.workflow;

import com.sdc.factor.common.utils.NULL;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 * 工作流节点操作，典型场景：点击某个按钮触发的一系列动作
 * 
 * @author Sean
 * @since 2019-03-24
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class WfrAction implements Serializable {

    /** 唯一标示 */
    private Integer id;

    /** 执行器代号，超类接口IWfrExecutor */
    private String exeCode;

    /** 执行器代号，超类接口IWfrExecutor */
    private String exeName;

    /** 执行器名称 */
    private String label;

    /** 下一状态代码，为空时(NULL)表示顺序执行 */
    private String nextCode;

    /** 下一状态名称 */
    private String nextName;

    /** 扩展标示，从IWfrExor.extTag()提取，临时存放在这里 */
    private String extTag;

    public static Predicate<WfrAction> fnSameId(Integer id) {
        return act -> NULL.nvl(act.id) == NULL.nvl(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WfrAction) {
            Integer thatId = ((WfrAction) obj).getId();
            return id != null && thatId != null && String.valueOf(id).equals(String.valueOf(thatId));
        }
        return false;
    }
}

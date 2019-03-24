package com.sdc.factor.common.workflow;

import java.math.BigDecimal;

/**
 * 零星类型集合，如：枚举等等
 * 
 * @author jbundle
 *
 */
public class WfrMiscTypes {

    /**
     * 审批流类型
     * 
     * @author jbundle
     *
     */
    public enum WfrFlowType {
        /** 测试 */
        TEST
    }

    /**
     * 执行审批动作时，要求用户输入金额项
     * 
     * @author jbundle
     */
    public static class WfrAmount {

        private String label;

        private String code;

        private BigDecimal amount;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

    }

    /**
     * 审批流状态标示
     * 
     * @author jbundle
     *
     */
    public enum WfrStatusTag {
        /** 无 */
        NONE;

        public boolean eq(WfrStatusTag s) {
            return s != null && s == this;
        }
    }

}
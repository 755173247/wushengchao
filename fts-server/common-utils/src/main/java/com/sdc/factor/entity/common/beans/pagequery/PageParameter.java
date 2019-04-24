package com.sdc.factor.entity.common.beans.pagequery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页查询mybatis xml查询自定义的parameterType
 *
 * @author nicholas
 * @since 2018/12/14 11:52
 */
public class PageParameter implements Serializable {

    // dao层用到的分页参数统一命名（Mybatis @Param注解中的命名）
    public static final String DAO_PAGE_PARAMETER_NAME = "pageParameter";

    // 当前分页类的 参数名-参数值 键值对Map的名称
    public static final String MAP_PARAMS_NAME = "parameter";

    // 分页查询条件，拼接的and条件短sql语句, 动态参数 eg: and a.id = #{id} and a.name = #{name}
    private List<String> conditions = new ArrayList<>();

    // 拼接的sql语句中所需要的参数名和参数值
    private Map<String, Object> parameter = new HashMap<>();

    // sql排序order by 后面的短句 eg：create_time desc, id asc .....
    private String orderSql = "";

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }

    public Map<String, Object> getParameter() {
        return parameter;
    }

    public void setParameter(Map<String, Object> parameter) {
        this.parameter = parameter;
    }

    public String getOrderSql() {
        return orderSql;
    }

    public void setOrderSql(String orderSql) {
        this.orderSql = orderSql;
    }

    @Override
    public String toString() {
        return "PageParameter{" +
                "conditions=" + conditions +
                ", parameter=" + parameter +
                ", orderSql='" + orderSql + '\'' +
                '}';
    }
}

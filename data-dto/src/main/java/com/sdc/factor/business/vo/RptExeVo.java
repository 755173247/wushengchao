package com.sdc.factor.business.vo;

import com.sdc.factor.business.entity.RptField;

import java.util.ArrayList;
import java.util.List;

/**
 * 报表执行结果Vo
 * 
 * @author Sean
 * @since 2019-03-24
 *
 */
public class RptExeVo {

    /** 执行结果的表头信息 */
    private final List<RptField> heads = new ArrayList<>();

    /** 执行结果数据列表 */
    private final List<Object[]> items = new ArrayList<>();

    public List<RptField> getHeads() {
        return heads;
    }

    public List<Object[]> getItems() {
        return items;
    }
}

package com.sdc.factor.entity.business.vo;

import com.sdc.factor.business.entity.*;
import com.sdc.factor.entity.business.entity.*;
import com.sdc.factor.entity.common.annotations.ContractVar;
import com.sdc.factor.entity.common.beans.KV;
import com.sdc.factor.entity.common.utils.DATE;
import com.sdc.factor.third.entity.business.entity.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;

import static java.util.Collections.unmodifiableList;

/**
 * 合同生成时，变量管理
 * 
 * @author Wu.Jian
 *
 */
public class FtsContractVars {

    /**
     * 变量模型
     */
    public static class Var extends KV {

        public final Function<Object, Object> fnValue;

        public Var(String k, String v, Function<Object, Object> fnValue) {
            super(k, v);
            this.fnValue = fnValue;
        }

    }

    public static final List<KV> COMMON_VARS = initCommonVars();

    public static final List<KV> PROJ_VARS = unmodifiableList(initVars(FtsProj.class, "p."));

    /** [[tr]]表示找到xml文档中，父tr节点并做循环；${[[tr]]a.buyerName} */
    public static final List<KV> ASSET_VARS = assetVars();

    private static FtsContractVars tmpl = null;

    public static FtsContractVars getInstance() {
        if (tmpl == null) {
            tmpl = new FtsContractVars();
        }
        return tmpl;
    }

    /** 企业字段展示 */
    public static List<KV> getEntVarList(String prefix) {
        return initVars(FtsEnt.class, prefix + ".");
    }

    private static List<KV> assetVars() {
        List<KV> ret = initVars(FtsAsset.class, "[[tr]]a.");

        ret.add(new KV("sumRealAmt", "债权金额合计"));
        ret.add(new KV("sumRealAmtDX", "债权金额合计大写"));
        ret.add(new KV("sumOtherAmt", "其他金额合计"));
        ret.add(new KV("sumOtherAmtDX", "其他金额合计大写"));
        return unmodifiableList(ret);
    }

    private static List<KV> initVars(Class<?> clazz, String prefix) {
        List<KV> ret = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            ContractVar tv = null;
            for (Annotation ann : field.getAnnotations()) {
                if (ContractVar.class.isAssignableFrom(ann.getClass())) {
                    tv = (ContractVar) ann;
                    break;
                }
            }

            if (tv == null) {
                continue;
            }

            ret.add(new KV(prefix + field.getName(), tv.name()));
        }
        return ret;
    }

    /** 通用变量 */
    private static List<KV> initCommonVars() {
        List<Var> ret = new ArrayList<>();
        ret.add(new Var("contractNum", "合同编号", contractNum -> contractNum));
        ret.add(new Var("factorNum", "保理合同编号", a -> ""));
        ret.add(new Var("transNum", "转让通知书编号", a -> ""));

        ret.add(new Var("#index#", "序号", a -> 1));
        ret.add(new Var("year", "年", a -> DATE.get(new Date(), Calendar.YEAR)));
        ret.add(new Var("month", "月", a -> DATE.get(new Date(), Calendar.MONTH) + 1));
        ret.add(new Var("day", "日", a -> DATE.get(new Date(), Calendar.DAY_OF_MONTH)));
        ret.add(new Var("date", "日期", a -> DATE.dateToStr(new Date(), "yyyy年MM月dd日")));
        return Collections.unmodifiableList(ret);
    }

    /**
     * 把通用变量值放到varMap
     * 
     * @param varMap
     */
    public static void commonVarValueTo(Map<String, Object> varMap, String contractNum) {
        for (KV v : COMMON_VARS) {
            varMap.put(v.getK(), ((Var) v).fnValue.apply(contractNum));
        }
    }

    /**
     * 把项目变量值放到varMap
     * 
     * @param varMap
     */
    public static void projVarValueTo(Map<String, Object> varMap, FtsProj proj) {
        varMap.put("p", proj);
    }

    /**
     * 把资产变量值放到varMap
     * 
     * @param varMap
     */
    public static void assetVarValueTo(Map<String, Object> varMap, List<FtsAsset> assetList) {
        varMap.put("a", assetList);
    }

    /**
     * 把企业变量值放到varMap
     * 
     * @param varMap
     */
    public static void entVarValueTo(Map<String, Object> varMap, FtsSigner signer) {
        varMap.put(signer.getProjRole().name(), signer.getEnt());
    }

    /**
     * 把企业变量值放到varMap
     * 
     * @param varMap
     * @param role
     * @param ent
     */
    public static void entVarValueTo(Map<String, Object> varMap, FtsProjEnt.FtsProjRole role, FtsEnt ent) {
        varMap.put(role.name(), ent);
    }

}

package com.sdc.factor.business.vo;

import com.sdc.factor.business.entity.FtsProj;
import com.sdc.factor.common.utils.NULL;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@Accessors(chain = true)
public class FtsProjFields {

    private static final Logger LOGGER = LoggerFactory.getLogger(FtsProjFields.class);

    /** 买方 */
    private String buyerName = "买方";

    /** 卖方 */
    private String sellerName = "卖方";

    /** 债权金额 */
    private String amount = "债权金额";

    /** 到期日 */
    private String dueDate = "到期日";

    /** 单据编号 */
    private String billNum = "单据编号";

    /** 其它金额 */
    private String extraAmt;

    /** 扩展属性1 */
    private String attr0;

    /** 扩展属性2 */
    private String attr1;

    /** 扩展属性3 */
    private String attr2;

    /** 扩展属性4 */
    private String attr3;

    /** 扩展属性5 */
    private String attr4;

    /** 扩展属性6 */
    private String attr5;

    /** 扩展属性7 */
    private String attr6;

    /** 扩展属性8 */
    private String attr7;

    /** 扩展属性9 */
    private String attr8;

    /** 扩展属性10 */
    private String attr9;

    /** 扩展属性11 */
    private String attr10;

    /** 扩展属性12 */
    private String attr11;

    /** 扩展属性13 */
    private String attr12;

    /** 扩展属性4 */
    private String attr13;

    /** 扩展属性15 */
    private String attr14;
    
    private String str;

    /**
     * 得到项目中配置的资产要素于赋予FtsProjFields对象
     * 
     * @param proj
     * @return
     */
    public FtsProjFields getProjFields(FtsProj proj) {
        FtsProjFields projFields = new FtsProjFields();
        List<String> fieldArr = Stream.of(StringUtils.split(proj.getAssetFields(), "||")).collect(Collectors.toList());
        List<String> fieldsList = new ArrayList<>();
        List<String> fieldsList1 = new ArrayList<>();
        for (String field : fieldArr) {
            int index = field.indexOf("=") + 1;
            fieldsList1.add(field.substring(index));
            if (StringUtils.isBlank(field.substring(index))) {
                continue;
            }
            fieldsList.add(field.substring(index));
        }
        if(fieldsList.size() < 1 ) {
           return projFields;
        }
        int j = 5;
        projFields.setBuyerName(NULL.nvl(fieldsList.get(0)));
        projFields.setSellerName(NULL.nvl(fieldsList.get(1)));
        projFields.setAmount(NULL.nvl(fieldsList.get(2)));
        projFields.setDueDate(NULL.nvl(fieldsList.get(3)));
        projFields.setBillNum(NULL.nvl(fieldsList.get(4)));
        if (StringUtils.isNotBlank(fieldsList1.get(5))) {
            projFields.setExtraAmt(fieldsList.get(5));
            j++;
        }
        int z = fieldsList.size() - j;
        for (int k = 0; k < z; k++) {
            projFields.setAttr(k, fieldsList.get(j));
            j++;
        }
        return projFields;
    }

    public String asString() {
        String fields = "buyerName=" + getBuyerName() + "||" + "sellerName=" + getSellerName() + "||" + "amount="
                + getAmount() + "||" + "dueDate=" + getDueDate() + "||" + "billNum=" + getBillNum() + "||"
                + "extraAmt=" + getExtraAmt();
        int i = 0;
        for(int index = 0 ;index < 15; index++) {
                if (StringUtils.isNotBlank(getAttr(index))) {
                   fields = fields + "||" + "attr"+i+"="+getAttr(index); 
                   i++;
                }
        }
        return fields;
    }

    public String getAttr(int index) {
        Field field = ReflectionUtils.findField(FtsProjFields.class,"attr" + index);
        if (field != null) {
            Object value = ReflectionUtils.getField(field, this);
            if (value != null) {
                return String.valueOf(value);
            }
        } else {
            LOGGER.error("Fail to find attr" + index + " in class " + FtsProjFields.class.getName());
        }
        return null;
    }

    public void setAttr(int index, String value) {
        Field field = ReflectionUtils.findField(FtsProjFields.class, "attr" + index);
        if (field != null) {
            ReflectionUtils.setField(field, this, value);
        } else {
            LOGGER.error("Fail to find attr" + index + " in class " + FtsProjFields.class.getName());
        }
    }
}

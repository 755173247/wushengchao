package com.sdc.factor.entity.common.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdc.factor.entity.common.beans.pagequery.AbstractPageBean;
import com.sdc.factor.entity.common.constants.PageRequestConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * 运营平台分页查询参数处理工具类
 *
 * @author nicholas
 * @since 2018-12-12 20:00
 */
public class PageQueryParser<T extends AbstractPageBean> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageQueryParser.class);

    /**
     * 模拟前端调用参数解析
     *
     * @param webParameters 分页查询请求参数
     * @return PageBean
     */
    public T analyzeRequestParams(Map<String, String[]> webParameters, Class<T> requestClazz) throws Exception {
        LOGGER.info("Analyze web parameters, original parameters are: {}", webParameters);
        String[] sortBy = webParameters.get(PageRequestConstants.PageRequestParamNames.SORT_BY);
        String[] sortOrder = webParameters.get(PageRequestConstants.PageRequestParamNames.SORT_ORDER);
        String[] filterMap = webParameters.get(PageRequestConstants.PageRequestParamNames.FILTER_MAP);

        Map<String, Object> resultMap = new HashMap<>();

        List<String> orderFieldList = new ArrayList<>();

        // 目前只支持一种排序，前端传值
        if (sortBy != null && sortBy.length > 0 && StringUtils.isNotBlank(sortBy[0])) {
            // 不使用StringUtils的完整分隔，值为Null的数据无法作为排序字段
            String[] sortBys = StringUtils.split(sortBy[0], ",");
            orderFieldList.addAll(Arrays.asList(sortBys));
        }

        String supportedOrder = null;
        if (sortOrder != null && sortOrder.length > 0 && StringUtils.isNotBlank(sortOrder[0])) {
            supportedOrder = sortOrder[0];
        }

        if (filterMap != null && filterMap.length > 0 && StringUtils.isNotBlank(filterMap[0])) {
            resultMap.putAll(analyzeFilterMap(filterMap[0]));
        }

        // 将排序的字段也放入Map中
        this.setOrderFields(resultMap, orderFieldList, supportedOrder);

        //  这里获取其他不在filterMap中的参数
        resultMap.putAll(this.getOtherParams(webParameters));

        // 封装完成的Map转换成对应的请求PageBean
        LOGGER.info("Convert to hashMap successfully: {}", resultMap);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T value;
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(resultMap);
            value = objectMapper.readValue(jsonString, requestClazz);
        } catch (JsonProcessingException e) {
            // 转换成PageBean的时候出现了异常，必须抛出，让对应的开发人员定位自己的问题
            LOGGER.error("Convert hashMap to entity failed, JsonProcessingException occur here", e);
            throw e;
        } catch (IOException e) {
            // 转换成PageBean的时候出现了异常，必须抛出，让对应的开发人员定位自己的问题
            LOGGER.error("Convert hashMap to entity failed, IOException occur here", e);
            throw e;
        }
        LOGGER.debug("jsonString is: " + jsonString);

        return value;
    }


    /**
     * 解析filterMap参数
     *
     * @param filterMapString filterMap参数的值
     * @return Map
     */
    private static Map<String, Object> analyzeFilterMap(String filterMapString) {
        LOGGER.info("Analyze filterMap");
        Map<String, Object> filterParamsMap = new HashMap<>();
        String[] filterParams = StringUtils.splitByWholeSeparatorPreserveAllTokens(filterMapString, ";");
        for (String oneFilterParam : filterParams) {
            filterParamsMap.putAll(analyzeOneParamOfFilterMap(oneFilterParam));
        }
        return filterParamsMap;
    }

    private static Map<String, Object> analyzeOneParamOfFilterMap(String filterParam) {
        LOGGER.info("Current param from filterMap is {}", filterParam);

        // result map
        Map<String, Object> resultMap = new HashMap<>();

        String[] filterData = StringUtils.splitByWholeSeparatorPreserveAllTokens(filterParam, ",");
        Map<String, Object> paramMap = new HashMap<>();
        if (filterData == null || filterData.length <= 2) {
            return resultMap;
        }

        paramMap.put("name", filterData[0]);

        List<Object> values = new ArrayList<>();

        String dataType = filterData[2];
        paramMap.put("dataType", StringUtils.isBlank(dataType) ? PageRequestConstants.RequestDataType.STRING : dataType);
        if (filterData.length > 3) {
            if (PageRequestConstants.RequestDataType.NUMBER.equals(dataType)) {
                paramMap.put("comparisonMode", filterData[3]);
            } else {
                values.add(filterData[3]);
            }
        }

        // 多值参数
        if (filterData.length > 4) {
            for (int i = 4; i < filterData.length; i++) {
                values.add(filterData[i]);
            }
        }
        paramMap.put("values", values);

        resultMap.put(filterData[0], paramMap);
        return resultMap;
    }

    /**
     * @param resultMap 设置好filterMap查询条件的Map
     * @param orderList 排序字段列表
     * @param sortOrder 支持的排序方式
     */
    private void setOrderFields(Map<String, Object> resultMap, List<String> orderList, String sortOrder) {
        if (orderList == null || orderList.isEmpty() || StringUtils.isBlank(sortOrder)) {
            return;
        }
        int sequence = 0;
        for (String orderField : orderList) {
            Map<String, Object> sortMap = null;
            Object sortObj = resultMap.get(orderField);
            if (sortObj == null) {
                sortMap = new HashMap<>();
            } else {
                sortMap = (Map<String, Object>) sortObj;
            }
            sortMap.put("supportSort", Boolean.TRUE);
            sortMap.put("orderSequence", sequence);
            sortMap.put("sortOrder", sortOrder);
            resultMap.put(orderField, sortMap);
            sequence++;
        }
    }

    /**
     * 解析分页查询请求中不在filterMap中的过滤条件参数
     *
     * @param webParameters 分页请求参数
     * @return 参数name-value 的键值对HashMap
     */
    private Map<String, Object> getOtherParams(Map<String, String[]> webParameters) {
        LOGGER.info("Get other parameters from web parameters");
        List<String> keys = new ArrayList<>();
        keys.add(PageRequestConstants.PageRequestParamNames.PAGE);
        keys.add(PageRequestConstants.PageRequestParamNames.PAGE_SIZE);
        keys.add(PageRequestConstants.PageRequestParamNames.SORT_BY);
        keys.add(PageRequestConstants.PageRequestParamNames.SORT_ORDER);
        keys.add(PageRequestConstants.PageRequestParamNames.FILTER_MAP);
        Map<String, Object> otherParams = new HashMap<>();
        // filterMap外的参数目前只支持一个name对应一个参数
        for (Map.Entry<String, String[]> entry : webParameters.entrySet()) {
            if (entry.getValue() == null || entry.getValue().length == 0
                    || StringUtils.isBlank(entry.getValue()[0]) || keys.contains(entry.getKey())) {
                continue;
            }
            otherParams.put(entry.getKey(), entry.getValue()[0]);
        }
        LOGGER.info("Other parameters contain: {}", otherParams);
        return otherParams;
    }

}

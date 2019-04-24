package com.sdc.factor.third.entity.controller;

import com.sdc.factor.third.entity.business.LogRequest;
import com.sdc.factor.third.entity.repository.business.LogRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wushengchao
 * @create 2019-04-22
 */
@RestController
@RequestMapping(value = "/logRequest")
public class LogRequestController {
    @Autowired
    private LogRequestRepository logRequestRepository;
    /** 增加操作 */
    @PostMapping(value = "/add")
    public LogRequest addLogRequest(LogRequest logRequest){
        logRequest.setLogRequestParams(logRequest.getLogRequestParams());
        logRequest.setLogRequestUrl(logRequest.getLogRequestUrl());
        logRequest.setServiceCode(logRequest.getServiceCode());
        logRequest.setSerializableNumber(logRequest.getSerializableNumber());
        logRequest.setVendorName(logRequest.getVendorName());
        return logRequestRepository.save(logRequest);
    }
    /** 根据id进行删除 */
    @DeleteMapping(value = "/delete/{logRequestId}")
    public void deleteLogRequest(@PathVariable("logRequestId") Long logRequestId){
        logRequestRepository.deleteById(logRequestId);
    }
    /** 通过id进行修改 */
    @PutMapping(value = "/update/{logRequestId}")
    public LogRequest updateLogRequest(@PathVariable("logRequestId") Long logRequestId,@RequestParam("logRequestParams")String logRequestParams,
                                       @RequestParam("logRequestUrl")String logRequestUrl,@RequestParam("serviceCode")String serviceCode,
                                       @RequestParam("serializableNumber")String serializableNumber,@RequestParam("vendorName")String vendorName){
            LogRequest logRequest=new LogRequest();
            logRequest.setLogRequestUrl(logRequestUrl);
            logRequest.setVendorName(vendorName);
            logRequest.setSerializableNumber(serializableNumber);
            logRequest.setServiceCode(serviceCode);
            logRequest.setLogRequestParams(logRequestParams);
            return logRequestRepository.save(logRequest);
    }
    /** 查询全部 */
    @GetMapping("/find")
    public List<LogRequest> findAllLogRequest(){
            return logRequestRepository.findAll();
    }
}

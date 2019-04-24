package com.sdc.factor.third.entity.controller;

import com.sdc.factor.third.entity.business.LogResponse;
import com.sdc.factor.third.entity.repository.business.LogResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wushengchao
 * @create 2019-04-22 16:26
 */
@RestController
@RequestMapping(value = "/logResponse")
public class LogResponseController {
    @Autowired
    private LogResponseRepository logResponseRepository;
    /** 增加操作 */
    @PostMapping(value = "/add")
    public LogResponse addLogRequest(LogResponse logResponse){
        logResponse.setErrorCode(logResponse.getErrorCode());
        logResponse.setErrorMessage(logResponse.getErrorMessage());
        logResponse.setVendorName(logResponse.getVendorName());
        logResponse.setSerializableNumber(logResponse.getSerializableNumber());
        logResponse.setData(logResponse.getData());
        logResponse.setServiceCode(logResponse.getServiceCode());
        return logResponseRepository.save(logResponse);
    }
    /** 根据id进行删除 */
    @DeleteMapping(value = "/delete/{logResponseId}")
    public void deleteLogResponse(@PathVariable("logResponseId") Long logResponseId){
        logResponseRepository.deleteById(logResponseId);
    }
    /** 通过id进行修改 */
    @PutMapping(value = "/update/{logResponseId}")
    public LogResponse updateLogResponse(@PathVariable("logResponseId") Long logResponseId, @RequestParam("errorCode")String errorCode,
                                         @RequestParam("errorMessage")String errorMessage, @RequestParam("vendorName")String vendorName,
                                         @RequestParam("serializableNumber")String serializableNumber, @RequestParam("data")String data,
                                         @RequestParam("serviceCode")String serviceCode){
        LogResponse logResponse=new LogResponse();
        logResponse.setErrorCode(errorCode);
        logResponse.setErrorMessage(errorMessage);
        logResponse.setSerializableNumber(serializableNumber);
        logResponse.setServiceCode(serviceCode);
        logResponse.setData(data);
        logResponse.setVendorName(vendorName);
        return logResponseRepository.save(logResponse);
    }
    /** 查询全部 */
    @GetMapping("/find")
    public List<LogResponse> findAllLogResponse(){
        return logResponseRepository.findAll();
    }
}

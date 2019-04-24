package com.sdc.factor.third.entity.controller;

import com.sdc.factor.third.entity.business.InvoiceResponse;
import com.sdc.factor.third.entity.repository.business.InvoiceResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wushengchao
 * @create 2019-04-22
 */
@RestController
@RequestMapping("/invoiceResponse")
public class InvoiceResponseController {
    @Autowired
    private InvoiceResponseRepository invoiceResponseRepository;
    /** 增加操作 */
    @PostMapping(value = "/add")
    public InvoiceResponse addInvoiceResponse(InvoiceResponse invoiceResponse){
        invoiceResponse.setStatus(invoiceResponse.getStatus());
        invoiceResponse.setDescription(invoiceResponse.getDescription());
        invoiceResponse.setInvoiceParamsSource(invoiceResponse.getInvoiceParamsSource());
        invoiceResponse.setSerializableNumber(invoiceResponse.getSerializableNumber());
        invoiceResponse.setPicture(invoiceResponse.getPicture());
        invoiceResponse.setBatchNumber(invoiceResponse.getBatchNumber());
        invoiceResponse.setServiceCode(invoiceResponse.getServiceCode());
        return invoiceResponseRepository.save(invoiceResponse);
    }
    /** 根据id进行删除 */
    @DeleteMapping(value = "/delete/{invoiceResponseId}")
    public void deleteInvoiceResponse(@PathVariable("invoiceResponseId") Long invoiceResponseId){
        invoiceResponseRepository.deleteById(invoiceResponseId);
    }
    /** 通过id进行修改 */
    @PutMapping(value = "/update/{invoiceResponseId}")
    public InvoiceResponse updateInvoiceResponse(@PathVariable("invoiceResponseId") Long invoiceResponseId, @RequestParam("status")Integer status,
                                         @RequestParam("description")String description, @RequestParam("invoiceParamsSource")String invoiceParamsSource,
                                         @RequestParam("picture")String picture, @RequestParam("batchNumber")String batchNumber,
                                         @RequestParam("serializableNumber")String serializableNumber,@RequestParam("serviceCode")String serviceCode){
        InvoiceResponse invoiceResponse=new InvoiceResponse();
        invoiceResponse.setStatus(status);
        invoiceResponse.setDescription(description);
        invoiceResponse.setInvoiceParamsSource(invoiceParamsSource);
        invoiceResponse.setSerializableNumber(serializableNumber);
        invoiceResponse.setPicture(picture);
        invoiceResponse.setServiceCode(serviceCode);
        invoiceResponse.setBatchNumber(batchNumber);
        return invoiceResponseRepository.save(invoiceResponse);
    }
    /** 查询全部 */
    @GetMapping("/find")
    public List<InvoiceResponse> findAllInvoiceResponse(){
        return invoiceResponseRepository.findAll();
    }
}

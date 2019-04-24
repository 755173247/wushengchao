package com.sdc.factor.third.entity.controller;

import com.sdc.factor.third.entity.business.InvoiceBusiness;
import com.sdc.factor.third.entity.repository.business.InvoiceBusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wushengchao
 * @create 2019-04-22
 */
@RestController
@RequestMapping(value = "/invoiceBusiness")
public class InvoiceBusinessController {
    @Autowired
    private InvoiceBusinessRepository invoiceBusinessRepository;
    /** 增加操作 */
    @PostMapping(value = "/add")
    public InvoiceBusiness addInvoiceBusiness(InvoiceBusiness invoiceBusiness){
        return invoiceBusinessRepository.save(addOrUpdate(invoiceBusiness));
    }
    /** 根据id进行删除 */
    @DeleteMapping(value = "/delete/{invoiceBusinessId}")
    public void deleteInvoiceBusiness(@PathVariable("invoiceBusinessId") Long invoiceBusinessId){
        invoiceBusinessRepository.deleteById(invoiceBusinessId);
    }
    /** 通过id进行修改 */
    @PutMapping(value = "/update/{invoiceBusinessId}")
    public InvoiceBusiness updateInvoiceBusiness(@PathVariable("invoiceBusinessId") Long invoiceBusinessId){
        InvoiceBusiness invoiceBusiness=new InvoiceBusiness();
        return invoiceBusinessRepository.save(addOrUpdate(invoiceBusiness));
    }
    /** 查询全部 */
    @GetMapping("/find")
    public List<InvoiceBusiness> findAllLogRequest(){
        return invoiceBusinessRepository.findAll();
    }
    /** 增加或修改需要调用的方法 */
    public InvoiceBusiness addOrUpdate(InvoiceBusiness invoiceBusiness){
        invoiceBusiness.setSerialNo(invoiceBusiness.getSerialNo());
        invoiceBusiness.setInvoiceCode(invoiceBusiness.getInvoiceCode());
        invoiceBusiness.setInvoiceNo(invoiceBusiness.getInvoiceNo());
        invoiceBusiness.setInvoiceDate(invoiceBusiness.getInvoiceDate());
        invoiceBusiness.setInvoiceMoney(invoiceBusiness.getInvoiceMoney());
        invoiceBusiness.setCheckCode(invoiceBusiness.getCheckCode());
        invoiceBusiness.setSalerName(invoiceBusiness.getSalerName());
        invoiceBusiness.setSalerTaxNo(invoiceBusiness.getSalerTaxNo());
        invoiceBusiness.setSalerAddressPhone(invoiceBusiness.getSalerAddressPhone());
        invoiceBusiness.setSalerAccount(invoiceBusiness.getSalerAccount());
        invoiceBusiness.setBuyerName(invoiceBusiness.getBuyerName());
        invoiceBusiness.setBuyerTaxNo(invoiceBusiness.getBuyerTaxNo());
        invoiceBusiness.setBuyerAddressPhone(invoiceBusiness.getBuyerAddressPhone());
        invoiceBusiness.setBuyerAccount(invoiceBusiness.getBuyerAccount());
        invoiceBusiness.setAmount(invoiceBusiness.getAmount());
        invoiceBusiness.setTaxAmount(invoiceBusiness.getTaxAmount());
        invoiceBusiness.setTotalAmount(invoiceBusiness.getTotalAmount());
        invoiceBusiness.setTotalAmountCn(invoiceBusiness.getTotalAmountCn());
        invoiceBusiness.setRemark(invoiceBusiness.getRemark());
        invoiceBusiness.setMachineNo(invoiceBusiness.getMachineNo());
        invoiceBusiness.setCancelMark(invoiceBusiness.getCancelMark());
        invoiceBusiness.setProxyMark(invoiceBusiness.getProxyMark());
        invoiceBusiness.setSnapshotUrl(invoiceBusiness.getSnapshotUrl());
        invoiceBusiness.setDownloadUrl(invoiceBusiness.getDownloadUrl());
        invoiceBusiness.setInvoiceType(invoiceBusiness.getInvoiceType());
        invoiceBusiness.setDrawer(invoiceBusiness.getDrawer());
        invoiceBusiness.setReviewer(invoiceBusiness.getReviewer());
        invoiceBusiness.setPayee(invoiceBusiness.getPayee());
        invoiceBusiness.setCheckCount(invoiceBusiness.getCheckCount());
        invoiceBusiness.setSerializableNumber(invoiceBusiness.getSerializableNumber());
        invoiceBusiness.setServiceCode(invoiceBusiness.getServiceCode());
        return invoiceBusiness;
    }

}

package com.sdc.factor.third.entity.controller;

import com.sdc.factor.service.InvoiceService;
import com.sdc.factor.third.entity.business.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wushengchao
 * @create 2019-04-23
 */
@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @PostMapping(value = "/invoice")
    public SysResult vatTransferThirdPartyApi(SynchronousReceiptRequest synchronousReceiptRequest) throws Exception {
        try {
            SysResult sysResult = invoiceService.vatTransferThirdPartyApi(synchronousReceiptRequest);
            if (sysResult.getErrorCode() == 0000 && sysResult.getData()==VatInvoice.class) {
             return SysResult.build(0000,"查询增值税发票成功",VatInvoice.class);
            }else if(sysResult.getErrorCode()==0000 && sysResult.getData()== MotorInvoice.class){
            return SysResult.build(0000,"查询机动车发票成功",MotorInvoice.class);
            }else if(sysResult.getErrorCode()==0000 && sysResult.getData()== SecondHandInvoice.class){
                return SysResult.build(0000,"查询二手车发票成功",SecondHandInvoice.class);
            }else if(sysResult.getErrorCode()==0000 && sysResult.getData()== MotorInvoice.class){
                return SysResult.build(0000,"查询通行费发票成功",MotorInvoice.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return SysResult.build(0001,"查询发票失败");
    }


}

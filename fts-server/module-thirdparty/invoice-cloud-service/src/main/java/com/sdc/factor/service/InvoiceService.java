package com.sdc.factor.service;

import com.sdc.factor.third.entity.business.SynchronousReceiptRequest;
import com.sdc.factor.third.entity.business.SysResult;

/**
 * 增值税发票接口
 * @author wushengchao
 * @create 2019-04-23
 */
public interface InvoiceService {
   SysResult vatTransferThirdPartyApi(SynchronousReceiptRequest synchronousReceiptRequest) throws Exception;
}

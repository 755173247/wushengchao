package com.sdc.factor.third.entity.repository.business;

import com.sdc.factor.third.entity.business.InvoiceResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wushengchao
 * @create 2019-04-22
 */
@Repository
public interface InvoiceResponseRepository extends JpaRepository<InvoiceResponse,Long> {
}

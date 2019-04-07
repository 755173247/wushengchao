package com.sdc.factor.repository.business.app;

import com.sdc.factor.business.entity.AppNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 通知DAO
 *
 * @author Sean
 * @since 2019-04-07
 */
@Repository
public interface IAppNoticeRepository extends JpaRepository<AppNotice, Long> {

}

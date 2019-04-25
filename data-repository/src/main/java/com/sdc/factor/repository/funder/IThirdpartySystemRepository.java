package com.sdc.factor.repository.funder;

import com.sdc.factor.funder.entity.ThirdpartySystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 第三方系统数据DAO
 *
 * @author Sean
 * @since 20019-04-20
 */
@Repository
public interface IThirdpartySystemRepository extends JpaRepository<ThirdpartySystem, Long> {

    /**
     * 根据appid查找第三方系统信息
     * @param appId
     * @return
     */
    ThirdpartySystem findByAppId(String appId);
}

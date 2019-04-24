package com.sdc.factor.entity.repository.business.project;

import com.sdc.factor.entity.business.entity.FtsProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 项目DAO
 *
 * @author Sean
 * @since 2019-04-07
 */
@Repository
public interface IProjectRepository extends JpaRepository<FtsProj, Long> {

}

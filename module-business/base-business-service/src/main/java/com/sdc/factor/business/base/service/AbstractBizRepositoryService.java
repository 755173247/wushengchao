package com.sdc.factor.business.base.service;

import com.sdc.factor.business.base.AbstractBizSrv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 公共业务抽象类，封装DAO的基础操作
 *
 * @author Sean
 * @since 2019-04-17
 *
 * @param <M> DAO类型
 * @param <E> 实体类型
 */
public abstract class AbstractBizRepositoryService<M extends JpaRepository<E, Long>, E> extends AbstractBizSrv implements IService<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBizRepositoryService.class);

    @Autowired
    protected M baseRepository;

    @Override
    public List<E> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public List<E> findAll(Sort sort) {
        return baseRepository.findAll(sort);
    }

    @Override
    public List<E> findAllById(Iterable<Long> ids) {
        return baseRepository.findAllById(ids);
    }

    @Override
    public <S extends E> List<S> saveAll(Iterable<S> entities) {
        return baseRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        baseRepository.flush();
    }

    @Override
    public <S extends E> S saveAndFlush(S entity) {
        return baseRepository.saveAndFlush(entity);
    }

    @Override
    public void deleteInBatch(Iterable<E> entities) {
        baseRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        baseRepository.deleteAllInBatch();
    }

    @Override
    public <S extends E> List<S> findAll(Example<S> example) {
        return baseRepository.findAll(example);
    }

    @Override
    public <S extends E> List<S> findAll(Example<S> example, Sort sort) {
        return baseRepository.findAll(example, sort);
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    @Override
    public <S extends E> S save(S entity) {
        return baseRepository.save(entity);
    }

    @Override
    public Optional<E> findById(Long id) {
        return baseRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return baseRepository.existsById(id);
    }

    @Override
    public long count() {
        return baseRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        this.baseRepository.deleteById(id);
    }

    @Override
    public void delete(E entity) {
        this.baseRepository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends E> entities) {
        this.baseRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        this.baseRepository.deleteAll();
    }

    @Override
    public <S extends E> Optional<S> findOne(Example<S> example) {
        return baseRepository.findOne(example);
    }

    @Override
    public <S extends E> Page<S> findAll(Example<S> example, Pageable pageable) {
        return baseRepository.findAll(example, pageable);
    }

    @Override
    public <S extends E> long count(Example<S> example) {
        return baseRepository.count(example);
    }

    @Override
    public <S extends E> boolean exists(Example<S> example) {
        return baseRepository.exists(example);
    }
}

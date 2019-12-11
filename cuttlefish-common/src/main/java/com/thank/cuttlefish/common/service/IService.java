package com.thank.cuttlefish.common.service;

import com.thank.cuttlefish.common.utils.Criteria;

import java.util.List;

public interface IService<T> {

    List<T> queryAll();

    List<T> queryList(T entity);

    List<T> queryByCriteria(Criteria<T, Object> criteria);

    T queryOneByCriteria(Criteria<T, Object> criteria);

    int countByCriteria(Criteria<T, Object> criteria);

    T queryOne(T entity);

    T queryById(Object id);

    int create(T entity);

    int createBatch(List<T> list);

    int updateAll(T entity);

    int updateNotNull(T entity);

    int delete(T entity);

    int deleteById(Object id);

    int count(T entity);

}

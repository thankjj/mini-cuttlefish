package com.thank.cuttlefish.base.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.thank.cuttlefish.base.mapper.MyMapper;
import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.pojo.dto.WrapperDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public abstract class BaseApi<T> implements Api<T> {

    @Autowired
    protected MyMapper<T> myMapper;

    @Override
    public Result<Integer> insert(T entity) {
        return Result.success(myMapper.insert(entity));
    }

    @Override
    public Result<Integer> deleteById(Integer id){
        return Result.success(myMapper.deleteById(id));
    }

    @Override
    public Result<Integer> updateById(T entity){
        return Result.success(myMapper.updateById(entity));
    }

    @Override
    public Result<T> selectById(Integer id){
        return Result.success(myMapper.selectById(id));
    }

    @Override
    public Result<T> selectOne(T entity){
        return Result.success(myMapper.selectOne(entity));
    }

    @Override
    public Result<List<T>> selectByMap(Map<String, Object> map){
        return Result.success(myMapper.selectByMap(map));
    }

    @Override
    public Result<List<T>> selectList(WrapperDto wrapperDto){
        EntityWrapper queryWrapper = new EntityWrapper();
        queryWrapper.setEntity(wrapperDto.getEntity());
        queryWrapper.allEq(wrapperDto.getWhereConditions());
        queryWrapper.orderBy(wrapperDto.getOrderByCondition(), wrapperDto.getOrderByIsAsc());
        return Result.success(myMapper.selectList(queryWrapper));
    }



}

package com.thank.cuttlefish.base.api.fallback;

import com.thank.cuttlefish.base.api.Api;
import com.thank.cuttlefish.common.enums.CommonResultStatus;
import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.pojo.dto.WrapperDto;

import java.util.List;
import java.util.Map;

public abstract class ApiFallback<T> implements Api<T> {
    @Override
    public Result<Integer> insert(T entity) {
        return Result.failure(CommonResultStatus.REMOTE_SERVICE_ERROR);
    }

    @Override
    public Result<Integer> deleteById(Integer id) {
        return Result.failure(CommonResultStatus.REMOTE_SERVICE_ERROR);
    }

    @Override
    public Result<Integer> updateById(T entity) {
        return Result.failure(CommonResultStatus.REMOTE_SERVICE_ERROR);
    }

    @Override
    public Result<T> selectById(Integer id) {
        return Result.failure(CommonResultStatus.REMOTE_SERVICE_ERROR);
    }

    @Override
    public Result<T> selectOne(T entity) {
        return Result.failure(CommonResultStatus.REMOTE_SERVICE_ERROR);
    }

    @Override
    public Result<List<T>> selectByMap(Map<String, Object> map) {
        return Result.failure(CommonResultStatus.REMOTE_SERVICE_ERROR);
    }

    @Override
    public Result<List<T>> selectList(WrapperDto wrapperDto) {
        return Result.failure(CommonResultStatus.REMOTE_SERVICE_ERROR);
    }



}

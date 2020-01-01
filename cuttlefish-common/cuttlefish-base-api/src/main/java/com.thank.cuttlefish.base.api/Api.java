package com.thank.cuttlefish.base.api;

import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.pojo.dto.WrapperDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

public interface Api<T> {
    @RequestMapping("/insert")
    Result<Integer> insert(@RequestBody T entity);

    @RequestMapping("/deleteById")
    Result<Integer> deleteById(@RequestBody Integer id);

    @RequestMapping("/updateById")
    Result<Integer> updateById(@RequestBody T entity);

    @RequestMapping("/selectById")
    Result<T> selectById(@RequestBody Integer id);

    @RequestMapping("/selectOne")
    Result<T> selectOne(@RequestBody T entity);

    @RequestMapping("/selectByMap")
    Result<List<T>> selectByMap(@RequestBody Map<String, Object> map);

    @RequestMapping("/selectList")
    Result<List<T>> selectList(@RequestBody WrapperDto wrapperDto);


}

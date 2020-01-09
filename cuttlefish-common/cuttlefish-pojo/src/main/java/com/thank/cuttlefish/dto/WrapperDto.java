package com.thank.cuttlefish.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class WrapperDto<T> {

    private T entity;

    private Map<String, Object> whereConditions = new HashMap<>();

    private String orderByCondition;

    private Boolean orderByIsAsc = true;


    public void addWhereCondition(String whereConditionName, Object whereConditionValue){
        this.whereConditions.put(whereConditionName, whereConditionValue);
    }

    public void addOrderByCondition(String column, Boolean isAsc){
        this.orderByCondition = column;
        this.orderByIsAsc = isAsc;
    }


}

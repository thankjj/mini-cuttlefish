package com.thank.cuttlefish.common.enums;

public enum ValidStatus {

    VALID(1, "有效"),
    IN_VALID(0, "无效"),
    ;

    private Integer value;
    private String msg;

    ValidStatus(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public Integer getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }

}

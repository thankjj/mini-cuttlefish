package com.thank.cuttlefish.common.exception;

import com.thank.cuttlefish.common.utils.ResultStatus;

public class CuttlefishException extends RuntimeException {

    ResultStatus status;

	public CuttlefishException(ResultStatus status) {
        //不生成栈追踪信息
        super(status.getMsg(), null, false, false);
        this.status = status;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }
}

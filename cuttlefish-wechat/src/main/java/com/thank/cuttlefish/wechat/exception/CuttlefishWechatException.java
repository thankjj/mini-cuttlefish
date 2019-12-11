package com.thank.cuttlefish.wechat.exception;

import com.thank.cuttlefish.common.exception.CuttlefishException;
import com.thank.cuttlefish.common.utils.ResultStatus;

public class CuttlefishWechatException extends CuttlefishException {

    public CuttlefishWechatException(ResultStatus status) {
        super(status);
    }
}

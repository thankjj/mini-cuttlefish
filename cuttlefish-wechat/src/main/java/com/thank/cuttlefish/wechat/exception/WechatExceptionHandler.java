package com.thank.cuttlefish.wechat.exception;

import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.wechat.enums.CuttlefishWechatResultStatus;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cjbi
 */
@ControllerAdvice
public class WechatExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(WechatExceptionHandler.class);

    /**
     * 微信服务调用失败的异常拦截
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(WxErrorException.class)
    public Result handleWxErrorException(HttpServletRequest request, WxErrorException e) {
        LOG.warn("execute method exception error.url is {}", request.getRequestURI(), e);
        return Result.failure(CuttlefishWechatResultStatus.WECHAT_SERVICE_ERROR.getCode(), e.getMessage());
    }

}

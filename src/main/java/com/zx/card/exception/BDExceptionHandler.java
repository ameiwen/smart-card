package com.zx.card.exception;

import com.zx.card.utils.HttpServletUtils;
import com.zx.card.utils.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理器
 */
@RestControllerAdvice
public class BDExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 捕捉权限不足异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(AuthorizationException e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        if (HttpServletUtils.jsAjax(request)) {
            return Result.error(403, "未授权");
        }
        return new ModelAndView("common/403");
    }


    @ExceptionHandler({Exception.class})
    public Object handleException(Exception e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        if (HttpServletUtils.jsAjax(request)) {
            return Result.error(500, "服务器错误，请联系管理员");
        }
        return new ModelAndView("common/500");
    }
}

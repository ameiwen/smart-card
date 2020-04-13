package com.zx.card.exception;


import com.zx.card.utils.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MainErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(
            value = {ERROR_PATH},
            produces = {"text/html"}
    )

    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        int code = response.getStatus();
        if (404 == code) {
            return new ModelAndView("common/404");
        } else if (403 == code) {
            return new ModelAndView("common/403");
        } else if (401 == code) {
            return new ModelAndView("index");
        } else {
            return new ModelAndView("common/500");
        }

    }

    @RequestMapping(value = ERROR_PATH)
    public Result handleError(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(200);
        int code = response.getStatus();
        if (404 == code) {
            return Result.error(404, "未找到资源");
        } else if (403 == code) {
            return Result.error(403, "没有访问权限");
        } else if (401 == code) {
            return Result.error(403, "登录过期");
        } else {
            return Result.error(500, "服务器错误");
        }
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
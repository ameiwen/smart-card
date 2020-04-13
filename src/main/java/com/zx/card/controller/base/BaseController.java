package com.zx.card.controller.base;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    /**
     * 组装参数
     */
    protected Map<String,String> buildParam(HttpServletRequest request){
        Map<String,String> params = new HashMap<>();
        Enumeration em = request.getParameterNames();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            String value = request.getParameter(name);
            if(StringUtils.isNotBlank(value)){
                params.put(name,value);
                request.setAttribute(name,value);
            }
        }
        return params;
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

}

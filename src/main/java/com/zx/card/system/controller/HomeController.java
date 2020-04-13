package com.zx.card.system.controller;

import com.zx.card.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/system")
public class HomeController extends BaseController {


    /***
     * 首页展示
     * @param request
     * @return
     */
    @GetMapping(value = "/home")
    public String home(HttpServletRequest request){
        return "system/home";
    }


}

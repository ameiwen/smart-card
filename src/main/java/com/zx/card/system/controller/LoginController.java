package com.zx.card.system.controller;

import com.zx.card.common.Constants;
import com.zx.card.system.model.Menu;
import com.zx.card.system.model.User;
import com.zx.card.system.model.vo.Tree;
import com.zx.card.system.service.MenuService;
import com.zx.card.system.service.UserService;
import com.zx.card.utils.AlgorithmUtil;
import com.zx.card.utils.Result;
import com.zx.card.utils.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    @GetMapping(value = "/index")
    public String index(HttpServletRequest request){
        //获取用户身份
        Integer userid = ShiroUtils.getUserId();
        //目录菜单加载
        List<Tree<Menu>> menus = menuService.selectMenuTreeByID(userid);
        request.setAttribute("menus", menus);
        request.setAttribute("name",ShiroUtils.getUser().getName());
        return "index";
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public Result login(String username, String password, String vercode, HttpServletRequest request){
        HttpSession session = request.getSession(true);
        String code = (String) session.getAttribute(Constants.PIC_CAPTCHA);
        if(StringUtils.isBlank(vercode)){
            return Result.error("请输入验证码");
        }
        if(!vercode.equals(code)){
            return Result.error("图片验证码错误");
        }
        session.removeAttribute(Constants.PIC_CAPTCHA);
        User user = userService.selectUserByUsername(username);
        if(user == null){
            return Result.error("用户或密码错误");
        }
        password = AlgorithmUtil.encrypt(password,user.getSalt());
        UsernamePasswordToken token = new UsernamePasswordToken(username, password,user.getSalt());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return Result.ok();
        } catch (AuthenticationException e) {
            return Result.error(e.getMessage());
        }
    }

}

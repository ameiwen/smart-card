package com.zx.card.controller;

import com.github.pagehelper.Page;
import com.zx.card.controller.base.BaseController;
import com.zx.card.model.Order;
import com.zx.card.service.OrderService;
import com.zx.card.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/trade/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;


    @GetMapping
    @RequiresPermissions(value = "trade:order:order")
    public String order() {
        return  "trade/order/order";
    }

    /**
     * 数据列表
     * @param page
     * @param order
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/list")
    @RequiresPermissions("trade:order:order")
    public Result list(Page<Order> page, Order order) {
        return orderService.selectOrderByPage(page,order);
    }

    /**
     * 添加页面
     * @return
     */
    @GetMapping(value = "/add")
    @RequiresPermissions("trade:order:add")
    public String add() {
        return "trade/order/add";
    }

    /**
     * 修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/edit/{id}")
    @RequiresPermissions("trade:order:edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Order order = orderService.selectOrderByID(id);
        model.addAttribute("order", order);
        return "trade/order/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping(value = "/save")
    @RequiresPermissions("trade:order:add")
    public Result saveOrder(Order order) {
        return orderService.saveOrder(order);
    }

    /**
     * 修改
     */
    @ResponseBody
    @PostMapping(value = "/update")
    @RequiresPermissions("trade:order:edit")
    public Result update(Order order) {
        return orderService.updateOrder(order);
    }

    /**
     * 删除
     */
    @ResponseBody
    @PostMapping(value = "/remove")
    @RequiresPermissions("trade:order:remove")
    public Result remove(Long id) {
        return orderService.removeOrder(id);
    }


}

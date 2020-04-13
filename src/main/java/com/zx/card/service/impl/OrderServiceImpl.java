package com.zx.card.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.dao.OrderDao;
import com.zx.card.model.Order;
import com.zx.card.service.OrderService;
import com.zx.card.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderMapper;

    @Override
    public Result selectOrderByPage(Page<Order> pageInfo, Order order) {
        Map<String,Object> search = new HashMap<>();
        if(StringUtils.isNotBlank(order.getOrderno())){
            search.put("orderno",order.getOrderno());
        }
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Order> list = orderMapper.selectOrderListWhere(search);
        PageInfo<Order> infos = new PageInfo<Order>(list);
        Result result = Result.ok("操作成功");
        result.put("data",infos);
        return result;
    }

    @Override
    public Order selectOrderByID(Long id) {
       return orderMapper.selectOrderByPrimaryKey(id);
    }

    @Override
    public Result saveOrder(Order order) {
        if(orderMapper.insertOrderSelective(order) > 0){
            return Result.ok("操作成功");
        }
        return Result.error("系统错误");
    }

    @Override
    public Result updateOrder(Order order) {
        if(orderMapper.updateOrderByPrimaryKeySelective(order) > 0){
            return Result.ok("操作成功");
        }
        return Result.error("系统错误");
    }

    @Override
    public Result removeOrder(Long id) {
        if(orderMapper.deleteOrderByPrimaryKey(id)>0) {
            return Result.ok("操作成功");
        }
        return Result.error("系统错误");
    }

}

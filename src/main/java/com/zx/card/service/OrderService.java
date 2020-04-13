package com.zx.card.service;

import com.github.pagehelper.Page;
import com.zx.card.model.Order;
import com.zx.card.utils.Result;

public interface OrderService {

    Result selectOrderByPage(Page<Order> pageInfo, Order order);

    Order selectOrderByID(Long id);

    Result saveOrder(Order order);

    Result updateOrder(Order order);

    Result removeOrder(Long id);

}

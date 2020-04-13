package com.zx.card.dao;

import com.zx.card.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OrderDao {

    Order selectOrderByPrimaryKey(Long id);

    List<Order> selectOrderListWhere(Map<String, Object> map);

    int insertOrderSelective(Order order);

    int updateOrderByPrimaryKeySelective(Order order);

    int deleteOrderByPrimaryKey(Long ID);

}


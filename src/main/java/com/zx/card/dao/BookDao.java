package com.zx.card.dao;

import com.zx.card.dao.generate.BookMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BookDao extends BookMapper {

}

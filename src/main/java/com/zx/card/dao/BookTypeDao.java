package com.zx.card.dao;

import com.zx.card.dao.generate.BookTypeMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BookTypeDao extends BookTypeMapper {

}

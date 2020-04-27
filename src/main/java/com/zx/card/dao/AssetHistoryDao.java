package com.zx.card.dao;

import com.zx.card.dao.generate.AssetHistoryMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AssetHistoryDao extends AssetHistoryMapper {
}

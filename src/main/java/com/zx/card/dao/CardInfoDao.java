package com.zx.card.dao;

import com.zx.card.dao.generate.CardInfoMapper;
import com.zx.card.model.CardInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CardInfoDao extends CardInfoMapper {

    @Select("select * from sc_card_info where user_id = #{userId}")
    CardInfo selectCardInfoByUserId(@Param("userId") Long userId);

}

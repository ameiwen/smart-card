package com.zx.card.dao;

import com.zx.card.dao.generate.BorrowRecordMapper;
import com.zx.card.model.dto.BookReDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BorrowRecordDao extends BorrowRecordMapper {

    @Select("SELECT a.type_name as name,sum(case when b.event_type = '1' then 1 else 0 end) as value " +
            "FROM sc_book_type a JOIN sc_borrow_record b " +
            "on a.id = b.type_id where b.event_type = '1'" +
            "group by a.type_name")
    List<BookReDO> selectReportForBookType();


}

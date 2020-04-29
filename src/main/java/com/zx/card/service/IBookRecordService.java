package com.zx.card.service;

import com.github.pagehelper.Page;
import com.zx.card.model.BorrowRecord;
import com.zx.card.utils.Result;

public interface IBookRecordService {

    Result selectBorrowRecordByPage(Page<BorrowRecord> pageInfo, BorrowRecord borrowRecord);

    Result selectCardInfoById(Long id);

    Result selectBookInfoById(Long id);

    Result saveBorrowRecord(BorrowRecord borrowRecord,String type);

    Result selectBookReport();

}

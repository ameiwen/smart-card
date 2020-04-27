package com.zx.card.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.dao.AssetHistoryDao;
import com.zx.card.model.AssetHistory;
import com.zx.card.model.AssetHistoryExample;
import com.zx.card.service.IRecordService;
import com.zx.card.utils.Result;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements IRecordService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AssetHistoryDao assetHistoryDao;

    @Override
    public Result selectRecordByPage(Page<AssetHistory> page, AssetHistory assetHistory) {
        try {
            AssetHistoryExample example = new AssetHistoryExample();
            if(assetHistory.getCardId() != null) {
                example.createCriteria().andCardIdEqualTo(assetHistory.getCardId());
            }
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<AssetHistory> list = assetHistoryDao.selectByExample(example);
            PageInfo<AssetHistory> infos = new PageInfo<>(list);
            return Result.ok("", infos);
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }
}

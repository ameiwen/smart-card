package com.zx.card.service;

import com.github.pagehelper.Page;
import com.zx.card.model.AssetHistory;
import com.zx.card.utils.Result;

public interface IRecordService {

    Result selectRecordByPage(Page<AssetHistory> page, AssetHistory assetHistory);


}

package com.zx.card.service;

import com.github.pagehelper.Page;
import com.zx.card.model.CardInfo;
import com.zx.card.utils.Result;

public interface ICardInfoService {

    Result selectCardInfoByPage(Page<CardInfo> page, CardInfo cardInfo);

    Result saveCardInfo(CardInfo cardInfo);

    CardInfo selectCardInfo(Long id);

    Result updateCardInfo(CardInfo cardInfo);

    Result lostCardInfo(Long id);

    Result selectUserInfoById(Long id,String role);

    Result selectCardInfoById(Long id);

}

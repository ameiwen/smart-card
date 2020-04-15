package com.zx.card.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.dao.SupermarketDao;
import com.zx.card.model.Supermarket;
import com.zx.card.model.SupermarketExample;
import com.zx.card.service.ISupermarketService;
import com.zx.card.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupermarketServiceImpl implements ISupermarketService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SupermarketDao supermarketDao;

    @Override
    public Result selectSupermarketByPage(Page<Supermarket> page, Supermarket supermarket) {
        try {
            SupermarketExample example = new SupermarketExample();
            SupermarketExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(supermarket.getLeader())) {
                criteria.andLeaderEqualTo(supermarket.getLeader());
            }
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<Supermarket> list = supermarketDao.selectByExample(example);
            PageInfo<Supermarket> infos = new PageInfo<>(list);
            return Result.ok("", infos);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Result saveSupermarket(Supermarket supermarket) {
        try {
            if (StringUtils.isBlank(supermarket.getLeader())) {
                return Result.error("领导人不能为空");
            }
            supermarketDao.insertSelective(supermarket);
            return Result.ok("操作成功");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

    @Override
    public Supermarket selectSupermarket(Long id) {
        try {
            if (id == null || id <= 0) {
                return null;
            }
            return supermarketDao.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    @Override
    public Result updateSupermarket(Supermarket supermarket) {
        try {
            if (supermarket.getId() == null || supermarket.getId() <= 0) {
                return Result.error("参数错误");
            }
            supermarketDao.updateByPrimaryKeySelective(supermarket);
            return Result.ok("操作成功");
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return Result.error("系统错误");
    }

}

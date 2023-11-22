package com.lz.applet.service.impl;

import com.lz.applet.entity.HisDataEntity;
import com.lz.applet.mapper.HisDataEntityMapper;
import com.lz.applet.service.HisDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service
public class HisDataServiceImpl implements HisDataService {

    @Autowired
    private HisDataEntityMapper hisDataEntityMapper;

    @Override
    public List<HisDataEntity> selectByTime(String starTime, String endTime) {
       return hisDataEntityMapper.selectByTime(starTime,endTime);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateAll(ArrayList<HisDataEntity> hisDataEntities2) {
       int result = hisDataEntityMapper.updateAll(hisDataEntities2);
       int byPrimaryKey = hisDataEntityMapper.deleteByPrimaryKey("ffe5dd070263245b00000006-");
       TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
       return 1;
    }
}

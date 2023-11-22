package com.lz.applet.mapper;

import com.lz.applet.entity.HisDataEntity;

import java.util.ArrayList;
import java.util.List;

public interface HisDataEntityMapper {
    int deleteByPrimaryKey(String dataId);

    int insert(HisDataEntity record);

    int insertSelective(HisDataEntity record);

    HisDataEntity selectByPrimaryKey(String dataId);

    int updateByPrimaryKeySelective(HisDataEntity record);

    int updateByPrimaryKey(HisDataEntity record);

    List<HisDataEntity> selectByTime(String starTime, String endTime);

    int updateAll(ArrayList<HisDataEntity> hisDataEntities2);

}
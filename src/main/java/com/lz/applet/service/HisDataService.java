package com.lz.applet.service;

import com.lz.applet.entity.HisDataEntity;

import java.util.ArrayList;
import java.util.List;

public interface HisDataService {
    List<HisDataEntity> selectByTime(String starTime,String endTime);

    int updateAll(ArrayList<HisDataEntity> hisDataEntities2);
}

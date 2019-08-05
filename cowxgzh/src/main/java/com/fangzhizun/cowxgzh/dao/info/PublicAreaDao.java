package com.fangzhizun.cowxgzh.dao.info;

import com.fangzhizun.cowxgzh.po.info.InfoHouse4store;

import java.util.List;

public interface PublicAreaDao {
    //查询公区房间信息
    public List<InfoHouse4store> selecePublicAreaList(InfoHouse4store infoHouse4store) throws Exception;
}

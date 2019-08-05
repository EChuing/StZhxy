package com.fangzhizun.cowxgzh.service.info;

import com.alibaba.fastjson.JSONArray;
import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.info.InfoHouse4store;
import com.fangzhizun.cowxgzh.po.sys.SysUsers;

public interface PublicAreaService {

    //查询公区房间信息
    public Result<JSONArray> selecePublicArea(InfoHouse4store infoHouse4store) throws Exception;
}

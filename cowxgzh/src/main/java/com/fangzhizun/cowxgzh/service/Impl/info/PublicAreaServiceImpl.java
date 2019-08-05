package com.fangzhizun.cowxgzh.service.Impl.info;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fangzhizun.cowxgzh.dao.info.PublicAreaDao;
import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.info.InfoHouse4store;
import com.fangzhizun.cowxgzh.service.info.PublicAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicAreaServiceImpl implements PublicAreaService {
    @Autowired
    private PublicAreaDao publicAreaDao;

    //查询公区房间信息
    public Result<JSONArray> selecePublicArea(InfoHouse4store infoHouse4store) throws Exception{
        List<InfoHouse4store> list=publicAreaDao.selecePublicAreaList(infoHouse4store);
        if (list.size()>0){
            JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
            return new Result<>(1,"查询成功",array);
        }else {
            return new Result<>(0,"查询数据为空",null);
        }
    }
}

package com.fangzhizun.cowxgzh.service.Impl.jour;

import com.alibaba.fastjson.JSON;
import com.fangzhizun.cowxgzh.dao.jour.JourScenarioPatternDescriptionDao;
import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.jour.JourScenarioPatternDescription;
import com.fangzhizun.cowxgzh.service.jour.JourScenarioPatternDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourScenarioPatternDescriptionServiceImpl implements JourScenarioPatternDescriptionService {
    @Autowired
    private JourScenarioPatternDescriptionDao jourScenarioPatternDescriptionDao;

    //查询情景描述表
    public Result<String> selecePatternName() throws Exception{
        List<JourScenarioPatternDescription> list = jourScenarioPatternDescriptionDao.selecePatternName();
        if(list.size()>0){
            String json = JSON.toJSONString(list);
            return new Result<>(1,"成功",json);
        }else {
            return new Result<>(0,"查询结果为空",null);
        }
    }
}

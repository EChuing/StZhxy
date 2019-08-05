package com.fangzhizun.cowxgzh.service.jour;

import com.fangzhizun.cowxgzh.entity.Result;


public interface JourScenarioPatternDescriptionService {

    //查询情景描述表
    public Result<String> selecePatternName() throws Exception;
}

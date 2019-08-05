package com.fangzhizun.cowxgzh.service.jour;

import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.jour.JourScenarioPattern;

public interface JourScenarioPatternService {

    //查询情景信息
    public Result<String> selectScene(JourScenarioPattern record) throws Exception;

    //保存情景指令
    public Result<String> updateScenario(JourScenarioPattern record)throws Exception;

    //执行情景指令
    public Result<String> openScenario(JourScenarioPattern record)throws Exception;
}

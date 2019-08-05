package com.fangzhizun.cowxgzh.dao.jour;

import com.fangzhizun.cowxgzh.po.jour.JourScenarioPattern;

import java.util.List;

public interface  JourScenarioPatternDao {

    //查询设备信息
    public List<JourScenarioPattern> selectDev(JourScenarioPattern record)throws  Exception;
    //查询情景信息
    public List<JourScenarioPattern> selectScene(JourScenarioPattern record)throws  Exception;
    //更新情景指令
    public int updateScenario (JourScenarioPattern record)throws Exception;
    //新增情景指令
    public int newAddScenario (JourScenarioPattern record)throws Exception;
    //查询情景模式
    public List<JourScenarioPattern> selectSceneMode(JourScenarioPattern record)throws  Exception;

}

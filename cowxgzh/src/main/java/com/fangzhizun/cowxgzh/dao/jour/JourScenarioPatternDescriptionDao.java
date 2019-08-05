package com.fangzhizun.cowxgzh.dao.jour;

import com.fangzhizun.cowxgzh.po.jour.JourScenarioPatternDescription;
import com.fangzhizun.cowxgzh.po.sys.SysUsers;

import java.util.List;

public interface JourScenarioPatternDescriptionDao {

    //用情景模式名称
    public List<JourScenarioPatternDescription> selecePatternName() throws Exception;
}

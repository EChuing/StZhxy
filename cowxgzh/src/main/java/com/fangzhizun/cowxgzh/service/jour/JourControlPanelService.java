package com.fangzhizun.cowxgzh.service.jour;

import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.jour.JourControlPanel;

import javax.xml.ws.Response;

public interface JourControlPanelService {
    //查询情景面板
    public Result<String> selectJourControlPanel(JourControlPanel result) throws Exception;

    //修改或者添加情景设置模式
    public Result<String> updatePanelMode(JourControlPanel result) throws Exception;

}

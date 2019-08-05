package com.fangzhizun.cowxgzh.dao.jour;

import com.fangzhizun.cowxgzh.po.jour.JourControlPanel;

import java.util.List;

public interface JourControlPanelDao {
    //查询设置情景面板信息
    public List<JourControlPanel> selectJourControlPanel(JourControlPanel record)throws  Exception;
    //新增情景面板模式
    public int insertPanelMode(JourControlPanel record)throws  Exception;
    //修改情景面板模式
    public int updataPanelMode(JourControlPanel record)throws  Exception;
}

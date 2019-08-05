package com.fangzhizun.cowxgzh.service.Impl.jour;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fangzhizun.cowxgzh.dao.jour.JourControlPanelDao;
import com.fangzhizun.cowxgzh.dao.jour.JourScenarioPatternDao;
import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.jour.JourControlPanel;
import com.fangzhizun.cowxgzh.po.jour.JourScenarioPattern;
import com.fangzhizun.cowxgzh.service.jour.JourControlPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourControlPanelServiceImpl implements JourControlPanelService {
    @Autowired
    private JourScenarioPatternDao jourScenarioPatternDao;
    @Autowired
    private JourControlPanelDao jourControlPanelDao;

    //查询情景面板
    public Result<String> selectJourControlPanel(JourControlPanel result) throws Exception{
        //接受参数
        Integer jhdHsId=result.getJhdHsId();
        JourScenarioPattern record=new JourScenarioPattern();
        record.setJsroHsId(jhdHsId);
        Integer cpDeviceId=result.getCpDeviceId();
        Integer cpKeyValue=result.getCpKeyValue();

        //查询情景指令模式
        List<JourScenarioPattern> listSce = jourScenarioPatternDao.selectSceneMode(record);
        if (listSce.size()>0){
            JourControlPanel jcp=new JourControlPanel();
            jcp.setCpKeyValue(cpKeyValue);
            jcp.setCpDeviceId(cpDeviceId);
            //查询保存的情景面板模式
            List<JourControlPanel> listCp=jourControlPanelDao.selectJourControlPanel(jcp);
            JSONObject json = new JSONObject();
            json.put("body1", listSce);
            json.put("body2", listCp);
            return new Result<String>(0, "查询成功", JSON.toJSONString(json));
        }else{
            return new Result<String>(0, "查询为空", null);
        }
    }
    //修改或者添加情景设置模式
    public Result<String> updatePanelMode(JourControlPanel result) throws Exception{


            System.out.println(result);
            //查询保存的情景面板模式
            List<JourControlPanel> listCp=jourControlPanelDao.selectJourControlPanel(result);
            System.out.println(listCp.size());
            int num=0;
            if (listCp.size()>0){
                //获值
                 Integer cpId=listCp.get(0).getCpId();
                 Integer cpDeviceId=result.getCpDeviceId();
                 Integer cpScenarioId=result.getCpScenarioId();
                 Integer cpKeyValue=result.getCpKeyValue();
                 //赋值
                 JourControlPanel jourControlPanel=new JourControlPanel();
                 jourControlPanel.setCpId(cpId);
                 jourControlPanel.setCpDeviceId(cpDeviceId);
                 jourControlPanel.setCpScenarioId(cpScenarioId);
                 jourControlPanel.setCpKeyValue(cpKeyValue);
                //修改情景设置模式
                 num=jourControlPanelDao.updataPanelMode(jourControlPanel);
            }else {
                //添加情景设置模式
                num=jourControlPanelDao.insertPanelMode(result);
            }
            if (num>0){
                return new Result<String>(1, "保存成功", null);
            }else{
                return new Result<String>(0, "保存失败", null);
            }
    }
}

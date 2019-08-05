package com.fangzhizun.cowxgzh.controller.jour;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.jour.JourControlPanel;
import com.fangzhizun.cowxgzh.po.jour.JourScenarioPattern;
import com.fangzhizun.cowxgzh.service.jour.JourControlPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class JourScenarioPanelController {
    @Autowired
    private JourControlPanelService jourControlPanelService;

    //查询情景面板信息
    @ResponseBody
    @RequestMapping("/selectSituationalPatterns")
    public Result<String> selectSituationalPatterns(HttpServletRequest request, Model model) {
        try {
            //赋值参数
            JourControlPanel jourControlPanel=new JourControlPanel();
            jourControlPanel.setCpKeyValue(Integer.parseInt(request.getParameter("type")));//键值
            jourControlPanel.setCpDeviceId(Integer.parseInt(request.getParameter("id")));//设备ID
            jourControlPanel.setJhdHsId(Integer.parseInt(request.getParameter("hsId")));//未租ID
            Result<String> result = jourControlPanelService.selectJourControlPanel(jourControlPanel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<String>(-2, "网络异常", null);
        }
    }
    //修改或者添加情景面板模式
    @ResponseBody
    @RequestMapping("/updatePanelMode")
    public Result<String> controlLight(HttpServletRequest request, Model model) {
        try {
            //赋值参数
            JourControlPanel jourControlPanel=new JourControlPanel();
            jourControlPanel.setCpKeyValue(Integer.parseInt(request.getParameter("cpKeyValue")));//情景面板键值
            jourControlPanel.setCpDeviceId(Integer.parseInt(request.getParameter("cpDeviceId")));//情景面板设备ID
            jourControlPanel.setCpScenarioId(Integer.parseInt(request.getParameter("cpScenarioId")));//情景指令ID
            Result<String> result = jourControlPanelService.updatePanelMode(jourControlPanel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<String>(-2, "网络异常", null);
        }
    }
}

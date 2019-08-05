package com.fangzhizun.cowxgzh.controller.jump;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JumpController {
    //登录成功后跳转页面
    @RequestMapping(value ="/renterIndex")
    public String renterIndex(){
        return "renterIndex.html";
    }
    //教室列表跳转
    @RequestMapping(value ="/commonAreaList")
    public String commonAreaList(HttpServletRequest request, Model model){
        String bottomNavigation = request.getParameter("bottomNavigation");
        String hsId = request.getParameter("hsId");
        model.addAttribute("bottomNavigation",bottomNavigation);
        model.addAttribute("hsId",hsId);
        return "commonAreaList.html";
    }
    //智能设备首页跳转
    @RequestMapping(value ="/deviceIndex")
    public String deviceIndex(HttpServletRequest request, Model model){
        String hsId = request.getParameter("hsId");
        model.addAttribute("hsId",hsId);
        return "deviceIndex.html";
    }
    //智能控制跳转
    @RequestMapping(value ="/commonScenarios")
    public String commonScenarios(HttpServletRequest request, Model model){
        String hsId = request.getParameter("hsId");
        model.addAttribute("hsId",hsId);
        return "commonScenarios.html";
    }
    //模式设置跳转
    @RequestMapping(value ="/modeSettings")
    public String modeSettings(HttpServletRequest request, Model model){
        String hsId = request.getParameter("hsId");
        model.addAttribute("hsId",hsId);
        return "modeSettings.html";
    }
    //情景模式跳转
    @RequestMapping(value ="/situatinalPatterns")
    public String situatinalPatterns(HttpServletRequest request, Model model){
        String hsId = request.getParameter("hsId");
        model.addAttribute("hsId",hsId);
        return "situatinalPatterns.html";
    }
    //情景设置跳转
    @RequestMapping(value ="/scenarioSetting")
    public String scenarioSetting(HttpServletRequest request, Model model){
        String spdId = request.getParameter("spdId");
        String hsId = request.getParameter("hsId");
        JSONObject giveParameter=new JSONObject();
        giveParameter.put("hsId",hsId);
        giveParameter.put("spdId",spdId);
        model.addAttribute("giveParameter", JSON.toJSONString(giveParameter));
        return "scenarioSetting.html";
    }
}

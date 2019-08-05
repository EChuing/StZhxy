package com.fangzhizun.cowxgzh.controller.jour;

import com.alibaba.fastjson.JSONArray;
import com.fangzhizun.cowxgzh.datasource.CheckoutDataSource;
import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.jour.JourScenarioPattern;
import com.fangzhizun.cowxgzh.service.jour.JourScenarioPatternService;
import com.fangzhizun.cowxgzh.util.AirConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class JourScenarioPatternController {
    @Autowired
    private JourScenarioPatternService jourScenarioPatternService;

    //查询情景信息
    @ResponseBody
    @RequestMapping("/selectScene")
    public Result<String> selectScene(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        try {
            //未租I
            Integer hsId =Integer.parseInt(request.getParameter("hsId"));
            //情景模式ID
            Integer jsroPatternId =Integer.parseInt(request.getParameter("jsroPatternId"));

            //获取用户信息
            String rentersession=(String)session.getAttribute("rentersession");
            JSONArray rentersessionArray=JSONArray.parseArray(rentersession);
            //获取用户Id
            Integer judUserId= Integer.parseInt(rentersessionArray.getJSONObject(0).getString("userCoding"));
            JourScenarioPattern jourScenarioPattern=new JourScenarioPattern();
            jourScenarioPattern.setJsroPatternId(jsroPatternId);
            jourScenarioPattern.setJsroHsId(hsId);
            jourScenarioPattern.setJudUserId(judUserId);
            Result<String> result = jourScenarioPatternService.selectScene(jourScenarioPattern);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<String>(-2, "网络异常", null);
        }
    }

    //修改或者添加情景设置模式
    @ResponseBody
    @RequestMapping("/updateScenario")
    public Result<String> updateScenario(HttpServletRequest request, Model model) {
            try{
                if (!("").equals(request.getParameter("company")) && request.getParameter("company")!=null){
                    //postMan请求接口获取公司名称简称
                    HttpSession session = request.getSession();
                    CheckoutDataSource checkoutDataSource = new CheckoutDataSource();
                    session.setAttribute("companyName",request.getParameter("company"));
                }
                Integer hsId =Integer.parseInt(request.getParameter("hsId"));
                String  jsroInstruction=request.getParameter("jsroInstruction");
                String jsroWxgzhState=request.getParameter("jsroWxgzhState");
                Integer jsroPatternId=Integer.parseInt(request.getParameter("jsroPatternId"));
                JourScenarioPattern jourScenarioPattern=new JourScenarioPattern();
                jourScenarioPattern.setJsroHsId(hsId);
                jourScenarioPattern.setJsroInstruction(jsroInstruction);
                jourScenarioPattern.setJsroWxgzhState(jsroWxgzhState);
                jourScenarioPattern.setJsroPatternId(jsroPatternId);
                Result<String> result = jourScenarioPatternService.updateScenario(jourScenarioPattern);
                return result;
            }catch (Exception e){
                e.printStackTrace();
                return new Result<String>(-2, "网络异常", null);
            }
    }
}

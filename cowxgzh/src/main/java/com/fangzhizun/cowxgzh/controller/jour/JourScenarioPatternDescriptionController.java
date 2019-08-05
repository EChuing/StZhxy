package com.fangzhizun.cowxgzh.controller.jour;
import com.fangzhizun.cowxgzh.datasource.CheckoutDataSource;
import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.jour.JourScenarioPattern;
import com.fangzhizun.cowxgzh.service.jour.JourScenarioPatternDescriptionService;
import com.fangzhizun.cowxgzh.service.jour.JourScenarioPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class JourScenarioPatternDescriptionController {
    @Autowired
    private JourScenarioPatternDescriptionService jourScenarioPatternDescriptionService;
    @Autowired
    private JourScenarioPatternService jourScenarioPatternService;

    //查询情景模式名称
    @ResponseBody
    @RequestMapping("/selecePatternName")
    public Result<String> selecePatternName(HttpServletRequest request, Model model) {
        try {
            Result<String> result = jourScenarioPatternDescriptionService.selecePatternName();
            System.out.println(result);
              return result;
        } catch (Exception e) {
            e.printStackTrace();
           return new Result<String>(-2, "网络异常", null);
        }
    }

    //执行情景指令
    @ResponseBody
    @RequestMapping("/openScenario")
    public Result<String> openScenario(HttpServletRequest request, Model model) {
        try {
            if (!("").equals(request.getParameter("company")) && request.getParameter("company")!=null){
                //postMan请求接口获取公司名称简称
                HttpSession session = request.getSession();
                CheckoutDataSource checkoutDataSource = new CheckoutDataSource();
                session.setAttribute("companyName",request.getParameter("company"));
            }
            //未租Id
            Integer jsroHsId=Integer.parseInt(request.getParameter("jsroHsId"));
            Integer jsroPatternId =Integer.parseInt(request.getParameter("jsroPatternId"));
            JourScenarioPattern jsro=new JourScenarioPattern();
            jsro.setJsroHsId(jsroHsId);
            jsro.setJsroPatternId(jsroPatternId);
            Result<String> result = jourScenarioPatternService.openScenario(jsro);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<String>(-2, "网络异常", null);
        }
    }
}

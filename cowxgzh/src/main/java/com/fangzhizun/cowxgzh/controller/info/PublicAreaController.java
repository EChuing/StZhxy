package com.fangzhizun.cowxgzh.controller.info;

import com.alibaba.fastjson.JSONArray;
import com.fangzhizun.cowxgzh.datasource.CheckoutDataSource;
import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.info.InfoHouse4store;
import com.fangzhizun.cowxgzh.po.sys.SysUsers;
import com.fangzhizun.cowxgzh.service.info.PublicAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PublicAreaController {

    @Autowired
    private PublicAreaService publicAreaService;

    //查询公区房间信息
    @ResponseBody
    @RequestMapping(value="/selecePublicArea")
    public Result<JSONArray> selecePublicArea(HttpServletRequest request, InfoHouse4store infoHouse4store) throws Exception{
        try {
            HttpSession session = request.getSession();
            if (!("").equals(request.getParameter("company")) && request.getParameter("company")!=null){
                //postMan请求接口获取公司名称简称
                CheckoutDataSource checkoutDataSource = new CheckoutDataSource();
                session.setAttribute("companyName",request.getParameter("company"));
            }
            String userCoding=(String)session.getAttribute("userCoding");//用户Id
            infoHouse4store.setSucUserId(Integer.parseInt(userCoding));
            Result<JSONArray> result= publicAreaService.selecePublicArea(infoHouse4store);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(-2,"系统异常",null);
        }
    }


}

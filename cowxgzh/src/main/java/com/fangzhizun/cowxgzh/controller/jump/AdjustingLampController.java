package com.fangzhizun.cowxgzh.controller.jump;

import com.alibaba.fastjson.JSONObject;
import com.fangzhizun.cowxgzh.util.HttpRequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.apache.http.ParseException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AdjustingLampController {
    /*页面跳转 传输参数*/
    //调节灯详情跳转
    @RequestMapping( "givAdjustingLamp")
    public String  giveYouParameter(HttpServletRequest request, Model model) throws ParseException, IOException {
//        String giveParameter = request.getParameter("giveParameter");
//        JSONObject jsonObj = new JSONObject();
//        try {
//            jsonObj =  JSONObject.parseObject(giveParameter);
//        } catch (Exception e) {
//            Map<String, String> map2 = new HashMap<String, String>();
//            map2.put("错误原因：", "数据格式转换错误！");
//            e.printStackTrace();
//            request.setAttribute("map2", map2);
//            return "error";
//        }
//        String sn = jsonObj.getString("sn");
//        String mac = jsonObj.getString("mac");
//        String type = jsonObj.getString("type");

        String sn = request.getParameter("sn");
        String mac = request.getParameter("mac");
        String type = request.getParameter("type");
        String status = HttpRequestUtil.gettingDeviceInformation(sn);
        if("".contentEquals(status)){
            return "error";
        }

        JSONObject deviceInformation = new JSONObject();
        deviceInformation.put("sn", sn);
        deviceInformation.put("mac", mac);
        deviceInformation.put("type", type);
        deviceInformation.put("status", status);
        System.out.println(deviceInformation);
        model.addAttribute("deviceInformation",deviceInformation);
        return "adjustingLamp.html";
    }
}

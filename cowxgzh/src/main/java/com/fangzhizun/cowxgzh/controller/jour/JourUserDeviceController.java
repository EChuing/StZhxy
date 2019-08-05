package com.fangzhizun.cowxgzh.controller.jour;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.jour.JourDevice;
import com.fangzhizun.cowxgzh.po.jour.JourUserDevice;
import com.fangzhizun.cowxgzh.service.jour.JourUserDeviceService;
import com.fangzhizun.cowxgzh.util.AirConvert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.fangzhizun.cowxgzh.util.HttpRequestUtil.post;

@Controller
public class JourUserDeviceController {
    private final static String YUNHAIPOSTURL = "http://www.fangzhizun.com/device/Interface/ControlDevice";
    @Autowired
    private JourUserDeviceService jourUserDeviceService;

    //查询设备信息
    @ResponseBody
    @RequestMapping("/selectUserDevice")
    public Result<String> selectUserDevice(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        try {
             Integer hsId =Integer.parseInt(request.getParameter("hsId"));  //未租ID
            //获取用户信息
            String rentersession=(String)session.getAttribute("rentersession");
            JSONArray rentersessionArray=JSONArray.parseArray(rentersession);
            Integer judUserId= Integer.parseInt(rentersessionArray.getJSONObject(0).getString("userCoding")); //获取用户Id
            //查询设备信息
            JourUserDevice jou=new JourUserDevice();
            jou.setJudUserId(judUserId);
            jou.setJhdHsId(hsId);
            Result<String> result = jourUserDeviceService.selectDeviceInformation(jou);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<String>(-2, "网络异常", null);
        }
    }

    //控制设备开关状态
    @ResponseBody
    @RequestMapping("/controlSwitch")
    public Result<String> controlSwitch(HttpServletRequest request, JourDevice jourDevice) {
        try {
            String devSwitchInstruction=jourDevice.getDevSwitchInstruction();
            JSONArray joutArray=JSONArray.parseArray(devSwitchInstruction);
            System.out.println("0000000"+joutArray);
            if (("10").equals(joutArray.getJSONObject(0).getString("devId"))){
                //开关状态
                String state = jourDevice.getDevState();
                String devId = joutArray.getJSONObject(0).getString("devId");
                String sn=joutArray.getJSONObject(0).getString("sn");
                String mac = joutArray.getJSONObject(0).getString("mac");
                String status=joutArray.getJSONObject(0).getString("status");
                Boolean isOn = true;
                if (("关").equals(state)) {
                    isOn = true;
                 } else {
                    isOn = false;
                }
                 String strCode = status.substring(4, 6) + status.substring(2,4); //码组号
                 String strMode = status.substring(6, 8);                  //模式
                 String strTemp = status.substring(8, 10);                //温度
                 String strSpeed = status.substring(10, 12);             //风量（风速）
                 int code = 0;                                          //码库设备
                 int temp = 0;
                 int mode = Integer.valueOf(strMode);                  //模式
                 int speed = Integer.valueOf(strSpeed);               //风速
                 int codeType = 0;                                    //功能
                 int type = Integer.valueOf(devId);
                 temp = Integer.parseInt(strTemp, 16) + 16;//温度范围
                 code = Integer.parseInt(strCode, 16);//码组号
                 AirConvert airConvert = new AirConvert();
                 //生成空调开关控制指令
                 status = airConvert.getState(type, code, isOn, temp, mode, speed, codeType);
                 //新的控制指令替换旧的
                 joutArray.getJSONObject(0).put("status",status);
                 devSwitchInstruction=JSON.toJSONString(joutArray);
             }
             Map<String, String> map = new HashMap<String, String>();
             map.put("controlsJson", devSwitchInstruction);
             String responseText = post(YUNHAIPOSTURL, map);
             JSONObject responseObj=JSONObject.parseObject(responseText);
             String code=responseObj.getString("code");
             String body=responseObj.getString("body");
             if (responseObj.getInteger("code") ==0){
                 String resultCode=JSONArray.parseArray(body).getJSONObject(0).getString("resultCode");
                 String resultMsg=JSONArray.parseArray(body).getJSONObject(0).getString("resultMsg");
                 if (("0").equals(resultCode)){
                     return new Result<String>(1, resultMsg, null);
                 }else {
                     return new Result<String>(0, resultMsg, null);
                 }
             }else {
                 return new Result<String>(-1, "失败", null);
                }
        } catch (Exception e) {
            e.printStackTrace();
//            return new Result<String>(-2, "设备不在线", null);
            return new Result<String>(-2, "网络异常", null);
        }
    }

}

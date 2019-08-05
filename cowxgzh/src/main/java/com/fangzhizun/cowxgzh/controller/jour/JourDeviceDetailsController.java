package com.fangzhizun.cowxgzh.controller.jour;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fangzhizun.cowxgzh.entity.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.fangzhizun.cowxgzh.util.HttpRequestUtil.post;

@Controller
public class JourDeviceDetailsController {
    private final static String POSTURL = "http://www.fangzhizun.com/device/Interface/ControlDevice";

    //2.4G冷暖灯控制
    @ResponseBody
    @RequestMapping("/controlLight")
    public Result<String> controlLight(HttpServletRequest request, Model model, @Param("obj") String obj) {
        try{
            JSONArray ary = new JSONArray();
            ary.add(JSONObject.parse(obj));
            String str = JSON.toJSONString(ary);
            Map<String, String> map = new HashMap<String, String>();
            map.put("controlsJson", str);
            String responseText = post(POSTURL, map);
            JSONObject  jsonObj = JSONObject.parseObject(responseText);
            String body=jsonObj.getString("body");
            String resultCode=JSONArray.parseArray(body).getJSONObject(0).getString("resultCode");
            String resultMsg=JSONArray.parseArray(body).getJSONObject(0).getString("resultMsg");
            if (("0").equals(jsonObj.getString("code")) && ("0").equals(resultCode)) {
                return new Result<String>(1, "开启成功", body);
            }
            else{
                return new Result<String>(0, resultMsg, body);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result<String>(-2, "网络异常", null);
        }

    }
}

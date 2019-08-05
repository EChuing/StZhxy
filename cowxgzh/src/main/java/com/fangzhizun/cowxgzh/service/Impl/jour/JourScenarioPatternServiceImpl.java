package com.fangzhizun.cowxgzh.service.Impl.jour;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fangzhizun.cowxgzh.dao.jour.JourScenarioPatternDao;
import com.fangzhizun.cowxgzh.dao.jour.JourUserDeviceDao;
import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.jour.JourDevice;
import com.fangzhizun.cowxgzh.po.jour.JourScenarioPattern;
import com.fangzhizun.cowxgzh.po.jour.JourScenarioPatternDescription;
import com.fangzhizun.cowxgzh.po.jour.JourUserDevice;
import com.fangzhizun.cowxgzh.service.jour.JourScenarioPatternService;
import com.fangzhizun.cowxgzh.util.AirConvert;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fangzhizun.cowxgzh.util.HttpRequestUtil.post;

@Service
public class JourScenarioPatternServiceImpl implements JourScenarioPatternService {
    private final static String POSTURL = "http://www.fangzhizun.com/device/Interface/QueryDeviceStatus2";
    private final static String OPENPOSTURL = "http://www.fangzhizun.com/device/Interface/ControlDevice";

    @Autowired
    private  JourScenarioPatternDao jourScenarioPatternDao;
    @Autowired
    private JourUserDeviceDao jourUserDeviceDao;

    //查询情景信息
    public Result<String> selectScene(JourScenarioPattern record) throws Exception{
        //获取未租Id
        Integer hsId=record.getJsroHsId();
        //获取用户Id
        Integer judUserId=record.getJudUserId();

        //查询设备信息
        JourUserDevice jou=new JourUserDevice();
        jou.setJudUserId(judUserId);
        jou.setJhdHsId(hsId);
        List<JourUserDevice> listDev=jourUserDeviceDao.selectDeviceInformation(jou);
        //初始化变量
        String responseText="";
        String status = "";
        String sn="";
        for (int i=0;i<listDev.size();i++){
            //给状态赋值null
            listDev.get(i).setDevStatus("123123");
            if (listDev.get(i).getDevBrandId()==20){
                if (listDev.get(i).getDevFirstType()==null || listDev.get(i).getDevSecondType()==null){
                    continue;
                }
                if (listDev.get(i).getDevFirstType()==4){
                    if (listDev.get(i).getDevSecondType()==4){
                        //获取设备在线状态
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("sns", listDev.get(i).getDevAuthId());
                        responseText = post(POSTURL, map);
                        JSONObject job = JSONObject.parseObject(responseText);
                        JSONArray body = JSONArray.parseArray(job.get("body").toString());
                        if (body.size()==0){
                            System.out.println("查码库，暂时没做");
                        }else{
                            for (int j = 0; j < body.size(); j++) {
                                status = body.getJSONObject(j).getString("status");
                                sn = body.getJSONObject(j).getString("sn");
                                if ((sn).equals(listDev.get(i).getDevAuthId())){
                                    System.out.println(status);
                                    System.out.println(i);
                                    listDev.get(i).setDevStatus(status);
                                    System.out.println(listDev.get(i).getDevStatus());
                                }
                            }
                        }
                    }
                }
            }
        }
        JSONObject json = new JSONObject();
        if (listDev.size()>0){
             //情景信息
            List<JourScenarioPattern> listSce = jourScenarioPatternDao.selectScene(record);
            json.put("body1", listDev);
            json.put("body2", listSce);
        }
        if(listDev.size()>0){
            String jsonStr = JSON.toJSONString(json);
            System.out.println(jsonStr);
            return new Result<>(1,"成功",jsonStr);
        }else {
            return new Result<>(0,"查询结果为空",null);
        }
    }
    //保存情景指令
    public Result<String> updateScenario(JourScenarioPattern record)throws Exception{
        //获取未租Id ，情景模式Id信息
        Integer jsroHsId=record.getJsroHsId();
        Integer jsroPatternId=record.getJsroPatternId();
        String  jsroInstruction=record.getJsroInstruction();
        String  jsroWxgzhState=record.getJsroWxgzhState();
        System.out.println(jsroInstruction);
        JSONArray jsroInstructionArray=JSONArray.parseArray(jsroInstruction);
        JSONArray jsroWxgzhStateArray=JSONArray.parseArray(jsroWxgzhState);
        //判断是否有空调，有生成空调开关指令
        for (int i=0;i<jsroInstructionArray.size();i++){
            if ((jsroInstructionArray.getJSONObject(i).getInteger("devBrandId"))==20){
                if (jsroInstructionArray.getJSONObject(i).getInteger("devFirstType")==4){
                    if (jsroInstructionArray.getJSONObject(i).getInteger("devSecondType")==4){
                        if (jsroInstructionArray.getJSONObject(i).getInteger("devId") == 10 && !("").equals(jsroInstructionArray.getJSONObject(i).getString("devId"))) {
                            //空调当前状态
                            String status = jsroInstructionArray.getJSONObject(i).getString("status");
                            int type = jsroInstructionArray.getJSONObject(i).getInteger("devId");   //设备类型
                            String strCode = status.substring(4, 6) + status.substring(2, 4); 	 //码组号
                            String strMode = status.substring(6, 8);               		 		//模式
                            String strTemp = status.substring(8, 10);              	 		 //温度
                            String strSpeed = status.substring(10, 12);            			//风量（风速）
                            int code = 0;                                            	   //码库设备
                            int temp = 0;
                            int mode = Integer.valueOf(strMode);                        //模式
                            int speed = Integer.valueOf(strSpeed);						//风速
                            int codeType = 0;                    //功能
                            Boolean isOn = true;                //开关
                            temp = Integer.parseInt(strTemp, 16) + 16;//温度范围
                            code = Integer.parseInt(strCode, 16);//码组号
                            String strState = jsroWxgzhStateArray.getJSONObject(i).getString("state");
                            if (("开").equals(strState)) {
                                isOn = true;
                            } else {
                                isOn = false;
                            }
                            AirConvert airConvert = new AirConvert();
                            String newStstus = airConvert.getState(type, code, isOn, temp, mode, speed, codeType);
                            //将新的开关指令替换旧的空调状态
                            jsroInstructionArray.getJSONObject(i).put("status", newStstus);
                            }
                        }
                    }
                }
            }
        //将新的信息存在实体类中
        JourScenarioPattern json=new JourScenarioPattern();
        json.setJsroWxgzhState(jsroWxgzhState);
        json.setJsroInstruction(JSON.toJSONString(jsroInstructionArray));
        json.setJsroPatternId(jsroPatternId);
        json.setJsroHsId(jsroHsId);
        List<JourScenarioPattern> jsro=jourScenarioPatternDao.selectScene(json);
        int result=0;
        if (jsro.size()>0){
            Integer jsroId=jsro.get(0).getJsroId();
            json.setJsroId(jsroId);
            //更新情景指令
            result=jourScenarioPatternDao.updateScenario(json);
        }else {
            //新增情景指令
             result=jourScenarioPatternDao.newAddScenario(json);
        }
        if(result>0){
            return new Result<>(1,"保存成功",null);
        }else {
            return new Result<>(0,"保存失败",null);
        }
    }
    //执行情景指令
    public Result<String> openScenario(JourScenarioPattern record) throws Exception{
         List<JourScenarioPattern> jsro=jourScenarioPatternDao.selectScene(record);
         if (jsro.size()>0){
             String jsroInstruction=jsro.get(0).getJsroInstruction();
             Map<String, String> map = new HashMap<String, String>();
             map.put("controlsJson", jsroInstruction);
             String responseText = post(OPENPOSTURL, map);
             JSONObject responseObj=JSONObject.parseObject(responseText);
//             String code=responseObj.getString("code");
             String body=responseObj.getString("body");
             Integer code=1;

             //新方案提示信息
             JSONArray bodyArray=JSONArray.parseArray(body);
             String resultMsg="";
             JSONObject  obj=new JSONObject();
             JSONArray objArray=new JSONArray();

             for (int i=0;i<bodyArray.size();i++){
                 if(responseObj.getInteger("code") ==0){
                     String resultCode=JSONArray.parseArray(body).getJSONObject(i).getString("resultCode");
                     resultMsg=JSONArray.parseArray(body).getJSONObject(i).getString("resultMsg");
                     String sn =JSONArray.parseArray(body).getJSONObject(i).getString("sn");
                     if (!("0").equals(resultCode)){
                         JourUserDevice jour=new JourUserDevice();
                        jour.setDevAuthId(sn);
                        List<JourUserDevice> listDev=jourUserDeviceDao.selectDeviceName(jour);
                        if (listDev.size()>0){
                            String devNickname=listDev.get(0).getDevNickname();
                            obj.put(devNickname,resultMsg);
                        }
                     }
                 }
                 else {
                     String devNickname="设备";
                     obj.put(devNickname,JSONObject.parseObject(body).getString("msg"));
                 }
             }
             if (obj.size()>0){
                 objArray.add(obj);
                 resultMsg=JSON.toJSONString(objArray);
                 code=-1;
             }
             return new Result<String>(code, resultMsg, null);
             //原方案提示信息
//             String resultCode=JSONArray.parseArray(body).getJSONObject(0).getString("resultCode");
//             String resultMsg=JSONArray.parseArray(body).getJSONObject(0).getString("resultMsg");
//            // if (responseObj.getInteger("code") ==0 && ("0").equals(resultCode)){
//             if (responseObj.getInteger("code") ==0){
//                 return new Result<String>(1, resultMsg, null);
//             }else {
//                 return new Result<String>(-1, resultMsg, null);
//             }
         }else {
             return new Result<String>(0, "请设置情景模式", null);
         }
    }
}

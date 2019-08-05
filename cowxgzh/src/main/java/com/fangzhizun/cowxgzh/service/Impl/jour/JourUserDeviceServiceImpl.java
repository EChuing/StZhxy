package com.fangzhizun.cowxgzh.service.Impl.jour;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fangzhizun.cowxgzh.dao.jour.JourUserDeviceDao;
import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.jour.JourDevice;
import com.fangzhizun.cowxgzh.po.jour.JourUserDevice;
import com.fangzhizun.cowxgzh.service.jour.JourUserDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fangzhizun.cowxgzh.util.HttpRequestUtil.post;

@Service
public class JourUserDeviceServiceImpl implements JourUserDeviceService {
    private final static String POSTURL = "http://www.fangzhizun.com/device/Interface/QueryDeviceStatus2";
    @Autowired
    private JourUserDeviceDao jourUserDeviceDao;
    //查询设备信息
    public Result<String> selectDeviceInformation(JourUserDevice record) throws Exception{
        String responseText = "";
        System.out.println(record.getJhdHsId());
        System.out.println(record.getJudUserId());
        List<JourUserDevice> list=jourUserDeviceDao.selectDeviceInformation(record);
        if (list.size()>0){
            String sn = "";
            String status = "";
            String devAuthId = "";
            JSONArray listArray = (JSONArray) JSON.toJSON(list);
            for (int i=0;i<list.size();i++){
                devAuthId = list.get(i).getDevAuthId();
                Map<String, String> map = new HashMap<String, String>();
                map.put("sns", devAuthId);
                responseText = post(POSTURL, map);
                JSONObject job1 = JSONObject.parseObject(responseText);
                JSONArray bodyArray = JSONArray.parseArray(job1.get("body").toString());
                if (bodyArray.size()>0){
                    for (int j = 0; j < bodyArray.size(); j++) {
                        status = bodyArray.getJSONObject(j).getString("status");
                        sn = bodyArray.getJSONObject(j).getString("sn");
                        if ((sn).equals(devAuthId)) {
                            listArray.getJSONObject(i).put("devStatus", status);
                        }
                    }
                }else {
                    listArray.getJSONObject(i).put("devStatus", status);
                }

            }
            String json = JSON.toJSONString(listArray);
            System.out.println(json);
            return new Result<>(1,"查询成功",json);
         }
         else {
            return new Result<>(0,"查询结果为空",null);
         }
    }

}

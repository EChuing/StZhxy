package com.fangzhizun.cowxgzh.service.jour;

import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.jour.JourDevice;
import com.fangzhizun.cowxgzh.po.jour.JourUserDevice;

public interface JourUserDeviceService {
    //查询设备信息
    public Result<String> selectDeviceInformation(JourUserDevice record) throws Exception;
}

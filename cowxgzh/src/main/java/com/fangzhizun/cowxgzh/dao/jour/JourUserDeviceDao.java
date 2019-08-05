package com.fangzhizun.cowxgzh.dao.jour;

import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.jour.JourDevice;
import com.fangzhizun.cowxgzh.po.jour.JourUserDevice;

import java.util.List;

public interface JourUserDeviceDao {
    //查询设备信息
    public List<JourUserDevice> selectDeviceInformation(JourUserDevice record) throws Exception;

    //根据SN查询设备名称
    public List<JourUserDevice> selectDeviceName(JourUserDevice record) throws Exception;

}

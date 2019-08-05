package com.fangzhizun.cowxgzh.service.Impl.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fangzhizun.cowxgzh.dao.sys.SysUsersDao;
import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.sys.SysUsers;
import com.fangzhizun.cowxgzh.service.sys.SysUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUsersServiceImpl implements SysUsersService {

    @Autowired
    private SysUsersDao sysUsersDao;

    //用账号密码登录
    @Override
    public Result<JSONArray> checkUserLoginByNamePssWord(SysUsers record) throws Exception {
        List<SysUsers> list = sysUsersDao.checkUserLoginByNamePssWord(record);
        if(list.size() > 0){
//            String json = JSON.toJSONString(list);
//            InfoPopulation ip = new InfoPopulation();
//            ip.setPopId(list.get(0).getJsrrPopId());
//            ip.setWxOpenid(record.getJsrrOpenid());
//            infoPopulationDao.emptyWxOpenId(ip);	//清空同一个微信之前登录过的用户的openid；防止一个微信id对应多个客户，导致查出其他客户订单
//            infoPopulationDao.updateByPrimaryKeySelective(ip);
            JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
            return new Result<>(1,"成功",array);
        }else{
            return new Result<>(0,"很抱歉！\n 您的身份证号码或者密码输入有误。",null);
        }
    }

    //更新openid信息、
    @Override
    public  void updateRenterOpenId (SysUsers record) throws Exception{
        sysUsersDao.updateRenterOpenId(record);
    }

    //用openid查询是否免密登录
    @Override
    public SysUsers getSysUsersPop(String openid) {
        SysUsers renter = sysUsersDao.getSysUsersPop(openid);
        return renter;
    }
}

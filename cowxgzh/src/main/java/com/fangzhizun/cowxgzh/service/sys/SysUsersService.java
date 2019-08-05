package com.fangzhizun.cowxgzh.service.sys;

import com.alibaba.fastjson.JSONArray;
import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.sys.SysUsers;

import java.util.List;

public interface SysUsersService {

    //用账号,密码检验登录
    public Result<JSONArray> checkUserLoginByNamePssWord(SysUsers record) throws Exception;

    //更新openid信息、
    public  void updateRenterOpenId (SysUsers record) throws Exception;

    //用openid查询是否免密登录
    public SysUsers getSysUsersPop(String openid);

}

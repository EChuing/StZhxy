package com.fangzhizun.cowxgzh.dao.sys;

import com.fangzhizun.cowxgzh.entity.Result;
import com.fangzhizun.cowxgzh.po.sys.SysUsers;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUsersDao {

    //用账号密码登录
    public  List<SysUsers> checkUserLoginByNamePssWord(SysUsers record) throws Exception;

    //更新openid信息、
    public void updateRenterOpenId (SysUsers record) throws Exception;

    //用openid查询是否免密登录
    public SysUsers getSysUsersPop(String openid);
}

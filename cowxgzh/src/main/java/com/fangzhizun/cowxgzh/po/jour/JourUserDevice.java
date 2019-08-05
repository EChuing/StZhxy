package com.fangzhizun.cowxgzh.po.jour;

public class JourUserDevice extends JourDevice{
    private  Integer judId ;        //用户表和设备管理主键
    private  Integer judUserId;     //用户ID
    private  Integer judDeviceId;   //设备Id
    private  String   judCreateTime;//创建时间

    public Integer getJudId() {
        return judId;
    }

    public void setJudId(Integer judId) {
        this.judId = judId;
    }

    public Integer getJudUserId() {
        return judUserId;
    }

    public void setJudUserId(Integer judUserId) {
        this.judUserId = judUserId;
    }

    public Integer getJudDeviceId() {
        return judDeviceId;
    }

    public void setJudDeviceId(Integer judDeviceId) {
        this.judDeviceId = judDeviceId;
    }

    public String getJudCreateTime() {
        return judCreateTime;
    }

    public void setJudCreateTime(String judCreateTime) {
        this.judCreateTime = judCreateTime;
    }

    @Override
    public String toString() {
        return "JourUserDevice{" +
                "judId=" + judId +
                ", judUserId=" + judUserId +
                ", judDeviceId=" + judDeviceId +
                ", judCreateTime='" + judCreateTime + '\'' +
                '}';
    }
}

package com.fangzhizun.cowxgzh.po.jour;

import com.fangzhizun.cowxgzh.po.info.InfoHouse4store;

import java.util.Date;

public class JourDevice extends JourHsDevice{
    private Integer id;

    private String devId;

    private Integer devBrandId;

    private String devType;

    private String devNickname;

    private String devUsername;

    private String devPassword;

    private String devSn;

    private String devAuthId;

    private String devAuthSecret;

    private String devSpare1;

    private String devSpare2;

    private String devOnly;

    private Date devTime;

    private String devDifference;

    private Integer devIdftId;

    private Integer devIdstId;

    private Integer devFirstType;

    private Integer devSecondType;

    private Integer devUserId;

    private Integer devRoad;

    private String devImg;

    //控制设备信息指令
    private String devSwitchInstruction;
    private String devState;
    private String devStatus;

    //用户与设备
    private Integer judUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public Integer getDevBrandId() {
        return devBrandId;
    }

    public void setDevBrandId(Integer devBrandId) {
        this.devBrandId = devBrandId;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public String getDevNickname() {
        return devNickname;
    }

    public void setDevNickname(String devNickname) {
        this.devNickname = devNickname;
    }

    public String getDevUsername() {
        return devUsername;
    }

    public void setDevUsername(String devUsername) {
        this.devUsername = devUsername;
    }

    public String getDevPassword() {
        return devPassword;
    }

    public void setDevPassword(String devPassword) {
        this.devPassword = devPassword;
    }

    public String getDevSn() {
        return devSn;
    }

    public void setDevSn(String devSn) {
        this.devSn = devSn;
    }

    public String getDevAuthId() {
        return devAuthId;
    }

    public void setDevAuthId(String devAuthId) {
        this.devAuthId = devAuthId;
    }

    public String getDevAuthSecret() {
        return devAuthSecret;
    }

    public void setDevAuthSecret(String devAuthSecret) {
        this.devAuthSecret = devAuthSecret;
    }

    public String getDevSpare1() {
        return devSpare1;
    }

    public void setDevSpare1(String devSpare1) {
        this.devSpare1 = devSpare1;
    }

    public String getDevSpare2() {
        return devSpare2;
    }

    public void setDevSpare2(String devSpare2) {
        this.devSpare2 = devSpare2;
    }

    public String getDevOnly() {
        return devOnly;
    }

    public void setDevOnly(String devOnly) {
        this.devOnly = devOnly;
    }

    public Date getDevTime() {
        return devTime;
    }

    public void setDevTime(Date devTime) {
        this.devTime = devTime;
    }

    public String getDevDifference() {
        return devDifference;
    }

    public void setDevDifference(String devDifference) {
        this.devDifference = devDifference;
    }

    public Integer getDevIdftId() {
        return devIdftId;
    }

    public void setDevIdftId(Integer devIdftId) {
        this.devIdftId = devIdftId;
    }

    public Integer getDevIdstId() {
        return devIdstId;
    }

    public void setDevIdstId(Integer devIdstId) {
        this.devIdstId = devIdstId;
    }

    public Integer getDevFirstType() {
        return devFirstType;
    }

    public void setDevFirstType(Integer devFirstType) {
        this.devFirstType = devFirstType;
    }

    public Integer getDevSecondType() {
        return devSecondType;
    }

    public void setDevSecondType(Integer devSecondType) {
        this.devSecondType = devSecondType;
    }

    public Integer getDevUserId() {
        return devUserId;
    }

    public void setDevUserId(Integer devUserId) {
        this.devUserId = devUserId;
    }

    public Integer getDevRoad() {
        return devRoad;
    }

    public void setDevRoad(Integer devRoad) {
        this.devRoad = devRoad;
    }

    public String getDevImg() {
        return devImg;
    }

    public void setDevImg(String devImg) {
        this.devImg = devImg;
    }

    public String getDevSwitchInstruction() {
        return devSwitchInstruction;
    }

    public void setDevSwitchInstruction(String devSwitchInstruction) {
        this.devSwitchInstruction = devSwitchInstruction;
    }

    public String getDevState() {
        return devState;
    }

    public void setDevState(String devState) {
        this.devState = devState;
    }

    public String getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(String devStatus) {
        this.devStatus = devStatus;
    }

    public Integer getJudUserId() {
        return judUserId;
    }

    public void setJudUserId(Integer judUserId) {
        this.judUserId = judUserId;
    }

    @Override
    public String toString() {
        return "JourDevice{" +
                "id=" + id +
                ", devId='" + devId + '\'' +
                ", devBrandId=" + devBrandId +
                ", devType='" + devType + '\'' +
                ", devNickname='" + devNickname + '\'' +
                ", devUsername='" + devUsername + '\'' +
                ", devPassword='" + devPassword + '\'' +
                ", devSn='" + devSn + '\'' +
                ", devAuthId='" + devAuthId + '\'' +
                ", devAuthSecret='" + devAuthSecret + '\'' +
                ", devSpare1='" + devSpare1 + '\'' +
                ", devSpare2='" + devSpare2 + '\'' +
                ", devOnly='" + devOnly + '\'' +
                ", devTime=" + devTime +
                ", devDifference='" + devDifference + '\'' +
                ", devIdftId=" + devIdftId +
                ", devIdstId=" + devIdstId +
                ", devFirstType=" + devFirstType +
                ", devSecondType=" + devSecondType +
                ", devUserId=" + devUserId +
                ", devRoad=" + devRoad +
                ", devImg='" + devImg + '\'' +
                ", devSwitchInstruction='" + devSwitchInstruction + '\'' +
                ", devState='" + devState + '\'' +
                ", devStatus='" + devStatus + '\'' +
                '}';
    }
}

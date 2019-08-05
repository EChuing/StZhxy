package com.fangzhizun.cowxgzh.po.jour;

public class JourHsDevice  extends  JourScenarioPatternDescription{
    private Integer jhdId;

    private Integer jhdHsId;

    private Integer jhdDeviceId;

    private String jhdTime;

    private Integer jhdSubDeviceNumber;

    public Integer getJhdId() {
        return jhdId;
    }

    public void setJhdId(Integer jhdId) {
        this.jhdId = jhdId;
    }

    public Integer getJhdHsId() {
        return jhdHsId;
    }

    public void setJhdHsId(Integer jhdHsId) {
        this.jhdHsId = jhdHsId;
    }

    public Integer getJhdDeviceId() {
        return jhdDeviceId;
    }

    public void setJhdDeviceId(Integer jhdDeviceId) {
        this.jhdDeviceId = jhdDeviceId;
    }

    public String getJhdTime() {
        return jhdTime;
    }

    public void setJhdTime(String jhdTime) {
        this.jhdTime = jhdTime;
    }

    public Integer getJhdSubDeviceNumber() {
        return jhdSubDeviceNumber;
    }

    public void setJhdSubDeviceNumber(Integer jhdSubDeviceNumber) {
        this.jhdSubDeviceNumber = jhdSubDeviceNumber;
    }

    @Override
    public String toString() {
        return "JourHsDevice{" +
                "jhdId=" + jhdId +
                ", jhdHsId=" + jhdHsId +
                ", jhdDeviceId=" + jhdDeviceId +
                ", jhdTime='" + jhdTime + '\'' +
                ", jhdSubDeviceNumber=" + jhdSubDeviceNumber +
                '}';
    }
}

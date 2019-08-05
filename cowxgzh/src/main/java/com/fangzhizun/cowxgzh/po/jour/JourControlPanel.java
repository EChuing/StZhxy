package com.fangzhizun.cowxgzh.po.jour;

public class JourControlPanel  extends JourHsDevice{
    private  Integer cpId;
    private  Integer cpScenarioId;
    private  Integer cpKeyValue;
    private  Integer cpDeviceId;

    public Integer getCpId() {
        return cpId;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    public Integer getCpScenarioId() {
        return cpScenarioId;
    }

    public void setCpScenarioId(Integer cpScenarioId) {
        this.cpScenarioId = cpScenarioId;
    }

    public Integer getCpKeyValue() {
        return cpKeyValue;
    }

    public void setCpKeyValue(Integer cpKeyValue) {
        this.cpKeyValue = cpKeyValue;
    }

    public Integer getCpDeviceId() {
        return cpDeviceId;
    }

    public void setCpDeviceId(Integer cpDeviceId) {
        this.cpDeviceId = cpDeviceId;
    }

    @Override
    public String toString() {
        return "JourControlPanel{" +
                "cpId=" + cpId +
                ", cpScenarioId=" + cpScenarioId +
                ", cpKeyValue=" + cpKeyValue +
                ", cpDeviceId=" + cpDeviceId +
                '}';
    }
}

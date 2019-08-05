package com.fangzhizun.cowxgzh.po.jour;


public class JourScenarioPattern  extends JourUserDevice{

    private Integer jsroId;

    private Integer jsroHsId;

    private String jsroWxgzhState;

    private String jsroImsState;

    private Integer jsroPatternId;

    private String jsroInstruction;

    private String spdDescribe;

    private String brightness;

    private String colorTemperature;

    public Integer getJsroId() {
        return jsroId;
    }

    public void setJsroId(Integer jsroId) {
        this.jsroId = jsroId;
    }

    public Integer getJsroHsId() {
        return jsroHsId;
    }

    public void setJsroHsId(Integer jsroHsId) {
        this.jsroHsId = jsroHsId;
    }

    public String getJsroWxgzhState() {
        return jsroWxgzhState;
    }

    public void setJsroWxgzhState(String jsroWxgzhState) {
        this.jsroWxgzhState = jsroWxgzhState;
    }

    public String getJsroImsState() {
        return jsroImsState;
    }

    public void setJsroImsState(String jsroImsState) {
        this.jsroImsState = jsroImsState;
    }

    public Integer getJsroPatternId() {
        return jsroPatternId;
    }

    public void setJsroPatternId(Integer jsroPatternId) {
        this.jsroPatternId = jsroPatternId;
    }

    public String getJsroInstruction() {
        return jsroInstruction;
    }

    public void setJsroInstruction(String jsroInstruction) {
        this.jsroInstruction = jsroInstruction;
    }

    @Override
    public String getSpdDescribe() {
        return spdDescribe;
    }

    @Override
    public void setSpdDescribe(String spdDescribe) {
        this.spdDescribe = spdDescribe;
    }

    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
    }

    public String getColorTemperature() {
        return colorTemperature;
    }

    public void setColorTemperature(String colorTemperature) {
        this.colorTemperature = colorTemperature;
    }

    @Override
    public String toString() {
        return "JourScenarioPattern{" +
                "jsroId=" + jsroId +
                ", jsroHsId=" + jsroHsId +
                ", jsroWxgzhState='" + jsroWxgzhState + '\'' +
                ", jsroImsState='" + jsroImsState + '\'' +
                ", jsroPatternId=" + jsroPatternId +
                ", jsroInstruction='" + jsroInstruction + '\'' +
                ", spdDescribe='" + spdDescribe + '\'' +
                ", brightness='" + brightness + '\'' +
                ", colorTemperature='" + colorTemperature + '\'' +
                '}';
    }
}

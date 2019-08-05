package com.fangzhizun.cowxgzh.po.jour;

public class JourScenarioPatternDescription {
    private Integer spdId;					//情景模式主键
    private Integer spdNumber; 				//公众号虚拟面板键值
    private String spdDescribe;				//情景模式描述
    private Integer spdPatternsAreVisible;	//客户端控制首页模式可见(1：可见，2：不可见)
    private Integer control;

    public Integer getSpdId() {
        return spdId;
    }

    public void setSpdId(Integer spdId) {
        this.spdId = spdId;
    }

    public Integer getSpdNumber() {
        return spdNumber;
    }

    public void setSpdNumber(Integer spdNumber) {
        this.spdNumber = spdNumber;
    }

    public String getSpdDescribe() {
        return spdDescribe;
    }

    public void setSpdDescribe(String spdDescribe) {
        this.spdDescribe = spdDescribe;
    }

    public Integer getSpdPatternsAreVisible() {
        return spdPatternsAreVisible;
    }

    public void setSpdPatternsAreVisible(Integer spdPatternsAreVisible) {
        this.spdPatternsAreVisible = spdPatternsAreVisible;
    }

    public Integer getControl() {
        return control;
    }

    public void setControl(Integer control) {
        this.control = control;
    }

    @Override
    public String toString() {
        return "JourScenarioPatternDescription{" +
                "spdId=" + spdId +
                ", spdNumber=" + spdNumber +
                ", spdDescribe='" + spdDescribe + '\'' +
                ", spdPatternsAreVisible=" + spdPatternsAreVisible +
                ", control=" + control +
                '}';
    }
}

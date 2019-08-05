package com.fangzhizun.cowxgzh.po.sys;

public class SysUserClass {

    private Integer sycId;
    private Integer sucUserId;
    private Integer sucClassId;

    public Integer getSycId() {
        return sycId;
    }

    public void setSycId(Integer sycId) {
        this.sycId = sycId;
    }

    public Integer getSucUserId() {
        return sucUserId;
    }

    public void setSucUserId(Integer sucUserId) {
        this.sucUserId = sucUserId;
    }

    public Integer getSucClassId() {
        return sucClassId;
    }

    public void setSucClassId(Integer sucClassId) {
        this.sucClassId = sucClassId;
    }

    @Override
    public String toString() {
        return "SysUserClass{" +
                "sycId=" + sycId +
                ", sucUserId=" + sucUserId +
                ", sucClassId=" + sucClassId +
                '}';
    }
}

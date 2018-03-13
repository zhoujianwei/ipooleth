package com.ipooleth.jpa.domain;

/**
 * Created by liusy on 17/9/4.
 */

import org.springframework.data.annotation.Id;

/**
 * 矿工
 */

public class Miner {

    @Id
    private String id;
    private String minePoolId;
    private String moneyAddress;
    private String remark;

    /**
     * 1:可用0:不可用
     */
    private int enable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMinePoolId() {
        return minePoolId;
    }

    public void setMinePoolId(String minePoolId) {
        this.minePoolId = minePoolId;
    }

    public String getMoneyAddress() {
        return moneyAddress;
    }

    public void setMoneyAddress(String moneyAddress) {
        this.moneyAddress = moneyAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
}

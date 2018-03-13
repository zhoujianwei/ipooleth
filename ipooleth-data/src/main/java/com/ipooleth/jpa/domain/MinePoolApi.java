package com.ipooleth.jpa.domain;

/**
 * Created by liusy on 17/9/4.
 */

/**
 * 矿池 API
 */
public class MinePoolApi {

    public enum KEY {
        STATS, MINERS, BLOCKS, PAYMENTS, ACCOUNTS;
    }

///api/stats
///api/miners
///api/blocks
///api/payments
///api/accounts/?


    private String key;

    private String url;

    private String remark;



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

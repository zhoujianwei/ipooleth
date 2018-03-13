package com.ipooleth.jpa.domain;

/**
 * Created by liusy on 17/9/4.
 */

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * 矿池
 */
public class MinePool {
    @Id
    private String id;
    private String nameZh;
    private String nameCn;
    private String remark;
    private List<MinePoolApi> minePoolApiList;
    /**
     * 1:可用0:不可用
     */
    private String enable;



    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public List<MinePoolApi> getMinePoolApiList() {
        return minePoolApiList;
    }

    public void setMinePoolApiList(List<MinePoolApi> minePoolApiList) {
        this.minePoolApiList = minePoolApiList;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}

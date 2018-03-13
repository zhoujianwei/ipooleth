package com.ipooleth.platform.services;

/**
 *  矿池服务接口定义
 *
 */
public interface EthService {


    public String stats(String  channel);

    public String miners(String channel);

    public String blocks(String channel);

    public String payments(String channel);

    public String accounts(String channel,String account);




    public String historyStats(String  channel);

    public String historyAccounts(String channel,String account);



}

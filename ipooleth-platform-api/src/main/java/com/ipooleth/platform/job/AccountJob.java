package com.ipooleth.platform.job;

import com.ipooleth.common.utils.DateUtil;
import com.ipooleth.common.utils.JasonUtil;
import com.ipooleth.common.utils.StringUtil;
import com.ipooleth.jpa.domain.MinePoolApi;
import com.ipooleth.platform.services.EthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 查询矿工每小时的状态 （每小第10秒时跑一次）
 */
@Component
@EnableScheduling
@EnableAutoConfiguration
public class AccountJob {

    private Logger logger = LoggerFactory.getLogger(AccountJob.class);


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private EthService ethService;



    @Scheduled(cron = "10 0 */1 * * ?")
    //@Scheduled(cron = "*/5 * * * * ? ")
    public void handleProcessor() {
        logger.debug("AccountJob handleProcessor start ......");
        String channel = "ETH";
        String minersData = null;
        String hkey = null;
        String date1 = null;
        String date2 = null;
        String account="";
        String accountValue="";
        try {
            //获取矿工帐号（钱包地址）
            minersData = ethService.miners(channel);
            Map<String,Object> map = JasonUtil.jsonToMap(minersData);
            Object obj = map.get("miners");
            if (null!=obj && obj instanceof Map)
            {
                map = (Map<String, Object>) obj;

                for (Map.Entry entry:map.entrySet())
                {
                    //矿工帐号（钱包地址）
                    account = String.valueOf(entry.getKey());
                    //查询当前矿工帐号（钱包地址）状态信息
                    accountValue = ethService.accounts(channel,account);

//                    Map<String,Object> mapAccount = JasonUtil.jsonToMap(accountValue);
//                    //查询当前矿工帐号（钱包地址）下有多少个矿机，且状态信息
//                    Object objAccount= mapAccount.get("workers");
//                    //在线矿工，总矿工数
//                    int onlineWorkers=0,workersTotal=0;
//                    if (null!=objAccount && objAccount instanceof Map)
//                    {
//                        Map<String,Object> workersMap= (Map<String, Object>) obj;
//                        onlineWorkers=0;workersTotal=0;
//                        for (Map.Entry entry_:workersMap.entrySet()){
//                            workersTotal++;
//                            Object workersStatsMap = entry_.getValue();
//                            if(null!=workersStatsMap && workersStatsMap instanceof  Map)
//                            {
//                                Object offline = ((Map)workersStatsMap).get("offline");
//                                //该矿机是否在线
//                                if(String.valueOf(offline).equals("true"))
//                                {
//                                    onlineWorkers++;
//                                }
//                            }
//                        }
//                    }
//                    mapAccount.put("onlineWorkers",onlineWorkers);
//                    mapAccount.put("workersTotal",workersTotal);


                    hkey = channel+ StringUtil.SIGN_COLON+ MinePoolApi.KEY.ACCOUNTS+StringUtil.SIGN_COLON+ account;
                    date1 = DateUtil.format(new Date(),"yyyy-MM-dd HH:00:00");
                    date2 = DateUtil.format(DateUtil.addDate(new Date(),-1),"yyyy-MM-dd HH:00:00");
                    //ETH:ACCOUNTS:0x52dfd2c40bcf1eeb9d06a8db438cc57335598572,2017-09-10 20:00:00,data
                    redisTemplate.opsForHash().put(hkey, date1,accountValue);
                    logger.debug("handleProcessor put hkey:"+hkey+" key:"+date1+" value:"+minersData);
                    long result = redisTemplate.opsForHash().delete(hkey,date2);
                    logger.debug("handleProcessor delete hkey:"+hkey+" key:"+date2+"    result:"+result);
                }

            }


        } catch (Exception e) {
            logger.error("AccountJob handleProcessor exception ",e);
        }
        logger.debug("handleProcessor hkey:"+hkey+" size:"+redisTemplate.opsForHash().size(hkey));
        logger.debug("AccountJob handleProcessor end ......");
    }



}

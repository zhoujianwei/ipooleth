package com.ipooleth.platform.job;

import com.ipooleth.common.utils.DateUtil;
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

/**
 * 查询矿池每小时的状态 （每小时跑一次）
 */
@Component
@EnableScheduling
@EnableAutoConfiguration
public class StatsJob {

    private Logger logger = LoggerFactory.getLogger(StatsJob.class);


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private EthService ethService;



    //@Scheduled(cron = "0 0 0/1 * * ?")
    @Scheduled(cron = "0 0/15 * * * ?")
    //@Scheduled(cron = "*/5 * * * * ? ")
    public void handleProcessor() {
        logger.debug("StatsJob handleProcessor start ......");
        String channel = "ETH";
        String statsData = ethService.stats(channel);
        String hkey = channel+ StringUtil.SIGN_COLON+ MinePoolApi.KEY.STATS;
        String date1 =  DateUtil.format(new Date(),"yyyy-MM-dd HH:00:00");
        String date2 =  DateUtil.format(DateUtil.addDate(new Date(),-1),"yyyy-MM-dd HH:00:00");
        //ETH:STATS,2017-09-10 20:00:00,data
        redisTemplate.opsForHash().put(hkey, date1,statsData);
        logger.debug("handleProcessor put hkey:"+hkey+" key:"+date1+" value:"+statsData);
        long result = redisTemplate.opsForHash().delete(hkey,date2);
        logger.debug("handleProcessor delete hkey:"+hkey+" key:"+date2+"    result:"+result);
        logger.debug("handleProcessor hkey:"+hkey+" size:"+redisTemplate.opsForHash().size(hkey));
        logger.debug("StatsJob handleProcessor end ......");
    }



}

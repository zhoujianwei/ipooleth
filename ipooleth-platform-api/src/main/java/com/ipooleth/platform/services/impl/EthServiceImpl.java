package com.ipooleth.platform.services.impl;

import com.ipooleth.common.utils.JasonUtil;
import com.ipooleth.common.utils.StringUtil;
import com.ipooleth.jpa.domain.MinePool;
import com.ipooleth.jpa.domain.MinePoolApi;
import com.ipooleth.jpa.repository.MinePoolRepository;
import com.ipooleth.platform.job.StatsJob;
import com.ipooleth.platform.services.EthService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 矿池服务接实现
 */
@Service
public class EthServiceImpl implements EthService {


    private org.slf4j.Logger logger = LoggerFactory.getLogger(StatsJob.class);


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private MinePoolRepository minePoolRepository;

    private Map<String, Object> cacheMap = new HashMap();

    private Object cacheMinePoolApi(MinePool minePool, MinePoolApi.KEY key) {
        Map<String, String> map;
        if (cacheMap.containsKey(minePool.getNameCn())) {
            map = (Map<String, String>) cacheMap.get(minePool.getNameCn());
            return map.get(key);
        } else {
            map = new HashMap<>();
            for (MinePoolApi minePoolApi : minePool.getMinePoolApiList()) {
                map.put(minePoolApi.getKey(), minePoolApi.getUrl());
            }
            cacheMap.put(minePool.getNameCn(), map);
            return cacheMinePoolApi(minePool, key);
        }
    }


    private String getMinePoolApiUrl(String channel,MinePoolApi.KEY key)
    {
        String url="";
        MinePool minePool = new MinePool();
        minePool.setNameCn(channel);
        minePool = minePoolRepository.findOne(Example.of(minePool));
        if (!StringUtil.isBlank(minePool.getId())) {
            for (MinePoolApi minePoolApi : minePool.getMinePoolApiList()) {
                if (key.name().equals(minePoolApi.getKey())) {
                    url = minePoolApi.getUrl();
                    break;
                }
            }
        }
        return url;
    }

    @Override
    public String stats(String channel) {
        String response = "";
        String url = getMinePoolApiUrl(channel,MinePoolApi.KEY.STATS);
        if (!StringUtil.isBlank(url)) {
            response = restTemplate.getForObject(url, String.class);
        }
        return response;
    }

    @Override
    public String miners(String channel) {
        String response = "";
        String url = getMinePoolApiUrl(channel,MinePoolApi.KEY.MINERS);
        if (!StringUtil.isBlank(url)) {
            response = restTemplate.getForObject(url, String.class);
        }
        return response;
    }

    @Override
    public String blocks(String channel) {
        String response = "";
        String url = getMinePoolApiUrl(channel,MinePoolApi.KEY.BLOCKS);
        if (!StringUtil.isBlank(url)) {
            response = restTemplate.getForObject(url, String.class);
        }
        return response;
    }

    @Override
    public String payments(String channel) {
        String response = "";
        String url = getMinePoolApiUrl(channel,MinePoolApi.KEY.PAYMENTS);
        if (!StringUtil.isBlank(url)) {
            response = restTemplate.getForObject(url, String.class);
        }
        return response;
    }

    @Override
    public String accounts(String channel, String account) {
        String response = "";
        String url = getMinePoolApiUrl(channel,MinePoolApi.KEY.ACCOUNTS);
        if (!StringUtil.isBlank(url)) {
            url = url.replace(StringUtil.SIGN_QUESTION_MARK,account);
            response = restTemplate.getForObject(url, String.class);
        }
        return response;
    }

    @Override
    public String historyStats(String channel) {
        String historyStats = "";
        try {
            //ETH:STATS
            Map<Object, Object> map = redisTemplate.opsForHash().entries(channel + StringUtil.SIGN_COLON + MinePoolApi.KEY.STATS);
            historyStats = JasonUtil.toJson(map);
        } catch (IOException e) {
            logger.error("EthServiceImpl historyStats exception,", e);
        }
        return historyStats;
    }

    @Override
    public String historyAccounts(String channel, String account) {
        String historyStats = "";
        try {
            //ETH:STATS
            Map<Object, Object> map = redisTemplate.opsForHash().entries(channel + StringUtil.SIGN_COLON + MinePoolApi.KEY.ACCOUNTS+ StringUtil.SIGN_COLON +account);
            historyStats = JasonUtil.toJson(map);
        } catch (IOException e) {
            logger.error("EthServiceImpl historyAccounts exception,", e);
        }
        return historyStats;
    }


    //    @Override
//    public String historyStats(String channel) {
//        redisTemplate.opsForHash().put("aaa:1","a1:1","a111");
//        redisTemplate.opsForHash().put("aaa:1","a1:2","a222");
//        redisTemplate.opsForHash().put("aaa:2","a2:1","a2");
//        redisTemplate.opsForHash().put("aaa:2","a2:2","a2");
//        redisTemplate.opsForHash().put("bbb","b:3","b3");
//
//        Object o =  redisTemplate.opsForHash().keys("aaa:1");
//        Object o1  =redisTemplate.getConnectionFactory().getConnection().keys("a*".getBytes());
//        Object o2 =  redisTemplate.opsForHash().keys("a*");
//        Object o3  =redisTemplate.getConnectionFactory().getConnection().hKeys("a*".getBytes());
//
//        redisTemplate.getConnectionFactory().getConnection().hSet("ccc:1".getBytes(),"c:1".getBytes(),"c1".getBytes());
//        Object c1  =redisTemplate.getConnectionFactory().getConnection().hKeys("c*".getBytes());
//        Object c2  =redisTemplate.getConnectionFactory().getConnection().hKeys("c:*".getBytes());
//        Object c3 =  redisTemplate.opsForHash().keys("c*");
//        Object c4 =  redisTemplate.opsForHash().keys("ccc:1");
//        Object c5 = redisTemplate.keys("c*");
//
//        Map<Object,Object> c6= redisTemplate.opsForHash().entries("aaa:1");
//
//        String c6_="";
//        try {
//            c6_ = JasonUtil.toJson(c6);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

}

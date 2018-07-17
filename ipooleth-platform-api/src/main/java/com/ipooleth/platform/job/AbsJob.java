package com.ipooleth.platform.job;

import com.ipooleth.common.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class AbsJob {

    @Autowired
    protected StringRedisTemplate redisTemplate;




    /**
     * 删除redis 中的数据 不包括（今天、昨天）
     *
     * @param key
     */
    protected void redisDataDelete(String key)
    {
        String date = DateUtil.format(new Date(),"yyyy-MM-dd");
        String date2 = DateUtil.format(DateUtil.addDate(new Date(),-1),"yyyy-MM-dd");
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        Map<Object, Object> mapResult = new HashMap<>();
        for (Map.Entry entry:map.entrySet())
        {
            if(entry.getKey().toString().indexOf(date)!=-1 || entry.getKey().toString().indexOf(date2)!=-1)
            {
                mapResult.put(entry.getKey(),entry.getValue());
            }
        }
        long result = redisTemplate.opsForHash().delete(key);
        redisTemplate.opsForHash().putAll(key,mapResult);
    }
}

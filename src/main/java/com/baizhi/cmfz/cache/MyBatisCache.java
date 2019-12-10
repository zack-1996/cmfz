package com.baizhi.cmfz.cache;

import com.baizhi.cmfz.util.SerializeUtils;
import com.baizhi.cmfz.util.SpringContextUtil;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by HIAPAD on 2019/12/6.
 */

/*
    1. 给一个有参构造及一个id属性
    2. 实现getId()方法
    3.
 */
public class MyBatisCache implements Cache{

    private String id;
    public MyBatisCache(String id){
        System.out.println(id);
        this.id = id;
    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("save:"+key);
        StringRedisTemplate template = (StringRedisTemplate) SpringContextUtil.getBean(StringRedisTemplate.class);
        String serialize = SerializeUtils.serialize(value);
        template.opsForHash().put(id,key.toString(),serialize);
    }

    @Override
    public Object getObject(Object key) {
        System.out.println("get"+key);
        StringRedisTemplate template = (StringRedisTemplate) SpringContextUtil.getBean(StringRedisTemplate.class);
        String o = (String) template.opsForHash().get(id, key.toString());
        if (o==null){
            return null;
        }
        Object o1 = SerializeUtils.serializeToObject(o);
        return o1;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {
        StringRedisTemplate template = (StringRedisTemplate) SpringContextUtil.getBean(StringRedisTemplate.class);
        template.delete(id);
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}

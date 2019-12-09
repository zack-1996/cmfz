package com.baizhi.cmfz.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

@Aspect
public class CacheAspect {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Around("@annotation(com.baizhi.cmfz.annotation.AddCache)")
    public Object addCache(ProceedingJoinPoint proceedingJoinPoint){
        //Key
        String key1=proceedingJoinPoint.getTarget().getClass().getName();
        //key
        String key=proceedingJoinPoint.getSignature().getName();
        Object[] args=proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            key += arg;
            key += ",";
        }
        //根据Key key查询缓存
        Object o = stringRedisTemplate.opsForHash().get(key1, key);
        if (o!=null){
            return o;
        }else{
            //执行原方法
            try {
                Object proceed = proceedingJoinPoint.proceed();
                stringRedisTemplate.opsForHash().put(key1,key,proceed);
                return proceed;
            }catch (Throwable throwable){
                throwable.printStackTrace();
                return null;
            }

        }

    }
    @Around("@annotation(com.baizhi.cmfz.annotation.DelCache)")
    public Object delCache(ProceedingJoinPoint proceedingJoinPoint){
        String name = proceedingJoinPoint.getTarget().getClass().getName();
        stringRedisTemplate.delete(name);
        try {
            Object proceed = proceedingJoinPoint.proceed();
            return proceed;
        }catch (Throwable throwable){
            throwable.printStackTrace();
            return null;
        }
    }
}

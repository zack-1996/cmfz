package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.dao.UserDao;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.service.GuanZhuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("guanzhu")
public class GuanZhuController {
    @Autowired
    private GuanZhuService guanZhuService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("add")
    public Map add(String uid,String id){

        Map map = guanZhuService.addGuanZhu(uid, id);
        //给redis中大师的信徒加上
        userDao.selectOne(new User().setId(uid));
        SetOperations<String, String> ops = stringRedisTemplate.opsForSet();
        ops.add(id,uid);
        return map;
    }
}

package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.dao.GuanZhuDao;
import com.baizhi.cmfz.dao.UserDao;
import com.baizhi.cmfz.entity.GuanZhu;
import com.baizhi.cmfz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("daoyou")
public class DaoYouController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private GuanZhuDao guanZhuDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping("show")
    public Map show(String uid){
        HashMap hashMap=new HashMap();
        //查找本用户关注的上师集合
        List<GuanZhu> guanZhus = guanZhuDao.findByUid(uid);
        //把上师id单独取出，放一个set集合中
        List<String> guruIds=new ArrayList<>();
        for(GuanZhu guanzhu:guanZhus){
            guruIds.add(guanzhu.getGuru_id());
        }
        //在redis中查找这些上师的信徒的交集
        SetOperations<String, String> ops = stringRedisTemplate.opsForSet();
        //单独取出一个key。之后和集合剩余的交集取并集
        String key1=guruIds.get(0);
        guruIds.remove(0);
        //返回上师信徒的交集
        Set<String> uids = ops.intersect(key1, guruIds);
        uids.remove(uid);
        if (uids!=null){
            //根据id查找用户
            List<User> users=new ArrayList<>();
            for (String s:uids){
                User user = userDao.selectOne(new User().setId(s));
                users.add(user);
            }
            hashMap.put("status","200");
            hashMap.put("users",users);
        }else {
            hashMap.put("status","-200");
            hashMap.put("message","查询失败");
        }
        return hashMap;
    }
}

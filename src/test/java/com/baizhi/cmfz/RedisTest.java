package com.baizhi.cmfz;

import com.baizhi.cmfz.dao.GuanZhuDao;
import com.baizhi.cmfz.entity.GuanZhu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfzApplication.class)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private GuanZhuDao guanZhuDao;
    @Test
    public void setTest(){
        //查找本用户关注的上师集合
        List<GuanZhu> guanZhus = guanZhuDao.findByUid("1");
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
        uids.remove("1");
        uids.forEach(uid-> System.out.println(uid));

    }
}

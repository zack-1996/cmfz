package com.baizhi.cmfz;

import com.baizhi.cmfz.dao.*;
import com.baizhi.cmfz.entity.*;
import com.baizhi.cmfz.util.MessageUtil;
import com.baizhi.cmfz.util.SecurityCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfzApplication.class)
public class InterfaceTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ArticleDao articleDao;
    @Test
    public void userDaoTest(){
        User user=new User().setPassword("2").setPhone("2");
        System.out.println(userDao.selectOne(user));
    }
    @Test
    public void albumDaoTest(){
        List<Article> byOrder =articleDao.findByOrder();
        byOrder.forEach(article -> System.out.println(article));
        List<Article> byGZ = articleDao.findByGZ("1");
        byGZ.forEach(article -> System.out.println(article));
    }
    @Autowired
    private CourseDao courseDao;
    @Test
    public void courseDao(){
        List<Course> byUserId;
        byUserId = courseDao.findByUserId("1");
        byUserId.forEach(course -> System.out.println(course));
    }
    @Test
    public void messageTest(){
        String code = SecurityCode.getSecurityCode();
        MessageUtil.sendMessage("15515242561",code);
        System.out.println(code);
    }

    @Autowired
    private CounterDao counterDao;
    @Test
    public void counterDaoTest(){
        List<Counter> counters = counterDao.findByUidAndCid("1", "1");
        counters.forEach(counter -> System.out.println(counter));
    }
    @Autowired
    private GuruDao guruDao;
    @Test
    public void guruDaoTest(){
        List<Guru> byGZ = guruDao.findByGZ("1");
        byGZ.forEach(guru -> System.out.println(guru));
    }

}

package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.UserDao;
import com.baizhi.cmfz.entity.MapVO;
import com.baizhi.cmfz.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Map login(String phone, String password) {
        HashMap hashMap=new HashMap();
        try {
            User user=new User().setPhone(phone).setPassword(password);
            User user1 = userDao.selectOne(user);
            hashMap.put("user",user1);
            hashMap.put("status",200);
        }catch (Exception e){
            hashMap.put("status",-200);
            hashMap.put("message","you phone or password is wrong");
            e.printStackTrace();
        }
        return hashMap;

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map findAll(Integer page, Integer rows) {
        HashMap hashMap=new HashMap();
        Integer start=(page-1)*rows;
        List<User> users = userDao.selectByRowBounds(new User(), new RowBounds(start, rows));
        Integer records = userDao.selectCount(new User());
        Integer total = records%rows==0?records/rows:records/rows+1;
        hashMap.put("rows",users);
        hashMap.put("page",page);
        hashMap.put("records",records);
        hashMap.put("total",total);
        return hashMap;
    }

    @Override
    public Map add(User user) {
        return null;
    }

    @Override
    public Map update(User user) {
        System.out.println(user);
        HashMap<String,Object> hashMap=new HashMap();
        userDao.updateByPrimaryKeySelective(user);
        hashMap.put("status",200);
        return hashMap;
    }

    @Override
    public Map delete(String id) {
        return null;
    }

    @Override
    public Map findUserCount() {
        HashMap hashMap = new HashMap();
        ArrayList<Object> man = new ArrayList<>();
        ArrayList<Object> women = new ArrayList<>();
        man.add(userDao.findCountSexAndDay("男",1));
        women.add(userDao.findCountSexAndDay("女",1));
        man.add(userDao.findCountSexAndDay("男",7));
        women.add(userDao.findCountSexAndDay("女",7));
        man.add(userDao.findCountSexAndDay("男",30));
        women.add(userDao.findCountSexAndDay("女",30));
        man.add(userDao.findCountSexAndDay("男",365));
        women.add(userDao.findCountSexAndDay("女",365));
        hashMap.put("men",man);
        hashMap.put("women",women);
        return hashMap;
    }

    @Override
    public List<MapVO> findByLocation() {
        List<MapVO> mapVOS = userDao.findByLocation();
        return mapVOS;
    }
}

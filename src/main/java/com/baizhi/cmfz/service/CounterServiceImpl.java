package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.CounterDao;
import com.baizhi.cmfz.entity.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class CounterServiceImpl implements CounterService {
    @Autowired
    private CounterDao counterDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map findByUidAndCid(String uid, String cid) {
        HashMap hashMap=new HashMap();
        try {
            List<Counter> counters = counterDao.findByUidAndCid(uid, cid);
            hashMap.put("status","200");
            hashMap.put("counters",counters);
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","查询功课失败");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }

    @Override
    public Map addCounter(String uid, String cid, String title) {
        HashMap hashMap=new HashMap();
        try {
            Counter counter=new Counter().setId(UUID.randomUUID().toString()).setTitle(title).setCount(0).setCreate_date(new Date()).setCourse_id(cid).setUser_id(uid);
            counterDao.insert(counter);
            List<Counter> counters = counterDao.findByUidAndCid(uid, cid);
            hashMap.put("status","200");
            hashMap.put("counters",counters);
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","查询功课失败");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }

    @Override
    public Map deleteCounter(String uid,String cid,String id) {
        HashMap hashMap=new HashMap();
        try {
            counterDao.deleteByPrimaryKey(new Counter().setId(id));
            List<Counter> counters = counterDao.findByUidAndCid(uid, cid);
            hashMap.put("status","200");
            hashMap.put("counters",counters);
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","查询功课失败");
            e.printStackTrace();
        }finally {
            return hashMap;
        }
    }

    @Override
    public Map updateCounter(String uid,String cid, String id, Integer count) {
        HashMap hashMap=new HashMap();
        try {
            counterDao.updateByPrimaryKeySelective(new Counter().setId(id).setCount(count));
            List<Counter> counters = counterDao.findByUidAndCid(uid, cid);
            hashMap.put("status","200");
            hashMap.put("counters",counters);
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","查询功课失败");
            e.printStackTrace();
        }finally {
            return hashMap;
        }

    }
}

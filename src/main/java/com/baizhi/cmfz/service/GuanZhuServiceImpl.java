package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.GuanZhuDao;
import com.baizhi.cmfz.dao.GuruDao;
import com.baizhi.cmfz.entity.GuanZhu;
import com.baizhi.cmfz.entity.Guru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Transactional
@Service
public class GuanZhuServiceImpl implements GuanZhuService {
    @Autowired
    private GuanZhuDao guanZhuDao;
    @Autowired
    private GuruDao guruDao;
    @Override
    public Map addGuanZhu(String uid, String gid) {
        HashMap hashMap=new HashMap();
        try {
            GuanZhu guanZhu=new GuanZhu().setId(UUID.randomUUID().toString()).setGuru_id(gid).setUser_id(uid);
            guanZhuDao.addGuanZhu(guanZhu);
            List<Guru> gurus = guruDao.findByGZ(uid);
            hashMap.put("status","200");
            hashMap.put("gurus",gurus);
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","添加关注上师失败");
            e.printStackTrace();
        }finally {
            return hashMap;
        }

    }
}

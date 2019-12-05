package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.GuruDao;
import com.baizhi.cmfz.entity.Guru;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruDao guruDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Guru> findAllAuthor() {
        List<Guru> gurus = guruDao.selectAll();
        return gurus;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> findAll(Integer page,Integer rows) {
        HashMap<String,Object> hashMap=new HashMap<>();
        //查询结果集合
        List<Guru> gurus = guruDao.selectByRowBounds(new Guru(), new RowBounds((page - 1) * rows, rows));
        //总条数
        int records = guruDao.selectCount(new Guru());
        //总页数
        int total=records%rows==0?records/rows:records/rows+1;
        hashMap.put("rows",gurus);
        hashMap.put("records",records);
        hashMap.put("total",total);
        hashMap.put("page",page);
        return hashMap;
    }

    @Override
    public Map<String, Object> add(Guru guru) {
        HashMap<String,Object> hashMap=new HashMap<>();
        guru.setId(UUID.randomUUID().toString());
        guruDao.insert(guru);
        hashMap.put("guruId",guru.getId());
        hashMap.put("status",200);
        return hashMap;

    }

    @Override
    public Map<String, Object> update(Guru guru) {
        HashMap<String,Object> hashMap=new HashMap();
        guru.setPhoto(null);
        hashMap.put("guruId",guru.getId());
        hashMap.put("status",200);
        return hashMap;

    }

    @Override
    public Map delete(String id) {
        HashMap<String,Object> hashMap=new HashMap();
        guruDao.deleteByPrimaryKey(id);
        hashMap.put("status",200);
        return hashMap;
    }

    @Override
    public Map<String, Guru> findAll() {
        HashMap hashMap=new HashMap();
        try {
            List<Guru> gurus = guruDao.selectAll();
            hashMap.put("status","200");
            hashMap.put("gurus",gurus);
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","查询失败");
        }finally {
            return hashMap;
        }

    }
}

package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.AlbumDao;
import com.baizhi.cmfz.entity.Album;
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
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map findAll(Integer page,Integer rows) {
        HashMap<String,Object> hashMap=new HashMap();
        List<Album> albums = albumDao.selectByRowBounds(new Album(), new RowBounds((page - 1) * rows, rows));
        Integer records = albumDao.selectCount(new Album());
        Integer total = records%rows==0?records/rows:records/rows+1;
        hashMap.put("rows",albums);
        hashMap.put("page",page);
        hashMap.put("records",records);
        hashMap.put("total",total);
        return hashMap;

    }

    @Override
    public Map add(Album album) {
        HashMap<String,Object> hashMap=new HashMap();
        String s= UUID.randomUUID().toString();
        album.setId(s);
        albumDao.insert(album);
        hashMap.put("albumId",s);
        hashMap.put("status",200);
        return hashMap;
    }

    @Override
    public Map update(Album album) {
        HashMap<String,Object> hashMap=new HashMap();
        album.setImg(null);
        albumDao.updateByPrimaryKeySelective(album);
        hashMap.put("albumId",album.getId());
        hashMap.put("status",200);
        return hashMap;
    }

    @Override
    public Map delete(String id) {
        HashMap<String,Object> hashMap=new HashMap();
        albumDao.deleteByPrimaryKey(id);
        hashMap.put("status",200);
        return hashMap;
    }
}

package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.ChapterDao;
import com.baizhi.cmfz.entity.Chapter;
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
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map findAll(Integer page, Integer rows,String id) {
        HashMap<String,Object> hashMap=new HashMap<>();
        List<Chapter> chapters = chapterDao.selectByRowBounds(new Chapter().setAlbum_id(id), new RowBounds((page - 1) * rows, rows));
        Integer records = chapterDao.selectCount(new Chapter());
        Integer total = records%rows==0?records/rows:records/rows+1;
        hashMap.put("rows",chapters);
        hashMap.put("page",page);
        hashMap.put("records",records);
        hashMap.put("total",total);
        return hashMap;
    }

    @Override
    public Map add(Chapter chapter) {
        HashMap<String,Object> hashMap=new HashMap<>();
        String s= UUID.randomUUID().toString();
        chapter.setId(s);
        chapterDao.insert(chapter);
        hashMap.put("chapterId",s);
        hashMap.put("status",200);
        return hashMap;
    }

    @Override
    public Map update(Chapter chapter) {
        HashMap<String,Object> hashMap=new HashMap();
        chapter.setUrl(null);
        chapterDao.updateByPrimaryKeySelective(chapter);
        hashMap.put("status",200);
        return hashMap;
    }

    @Override
    public Map delete(String id) {
        HashMap<String,Object> hashMap=new HashMap();
        chapterDao.deleteByPrimaryKey(id);
        hashMap.put("status",200);
        return hashMap;
    }
}

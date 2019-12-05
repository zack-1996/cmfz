package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.ArticleDao;
import com.baizhi.cmfz.dao.GuruDao;
import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.entity.Guru;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional()
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private GuruDao guruDao;
    @Override
    public Map<String, Object> findAll(Integer page, Integer rows) {
        HashMap<String, Object> hashMap=new HashMap<>();
        List<Article> articles = articleDao.selectByRowBounds(new Article(), new RowBounds((page - 1) * rows, rows));
        for (Article article:articles){
            Guru guru = guruDao.selectByPrimaryKey(article.getGuru_id());
            article.setGuru(guru);
        }
        //总条数
        int records = articleDao.selectCount(new Article());
        //总页数
        Integer total=records%rows==0?records/rows:records/rows+1;
        hashMap.put("rows",articles);
        hashMap.put("records",records);
        hashMap.put("total",total);
        hashMap.put("page",page);
        return hashMap;

    }

    @Override
    public Map<String, Object> update(Article article) {
        HashMap<String,Object> hashMap=new HashMap();
        article.setGuru_id(null);
        article.setImg(null);
        hashMap.put("articleId",article.getId());
        hashMap.put("status",200);
        articleDao.updateByPrimaryKeySelective(article);
        return hashMap;
    }

    @Override
    public Map<String, Object> add(Article article) {
        System.out.println(article);
        HashMap<String,Object> hashMap=new HashMap();
        article.setId(UUID.randomUUID().toString());
        articleDao.insert(article);
        hashMap.put("articleId",article.getId());
        hashMap.put("status",200);
        return hashMap;
    }

    @Override
    public Map<String, Object> delete(String id) {
        HashMap<String,Object> hashMap=new HashMap();
        articleDao.deleteByPrimaryKey(id);
        hashMap.put("status",200);
        return hashMap;
    }
}

package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Article;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleDao extends Mapper<Article> {
    List<Article> findByOrder();
    List<Article> findByGZ(String uid);
}

package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Article;

import java.util.Map;

public interface ArticleService {
    Map<String,Object> findAll(Integer page,Integer rows);
    Map<String,Object> update(Article article);
    Map<String,Object> add(Article article);
    Map<String,Object> delete(String id);
}

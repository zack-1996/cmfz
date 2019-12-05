package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Guru;

import java.util.List;
import java.util.Map;

public interface GuruService {
    List<Guru> findAllAuthor();
    Map<String,Object> findAll(Integer page,Integer rows);
    Map<String,Object> add(Guru guru);
    Map<String,Object> update(Guru guru);
    Map<String,Object> delete(String id);
    Map findAll();

}

package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    Map findAll(Integer page, Integer rows,String id);
    Map add(Chapter chapter);
    Map update(Chapter chapter);
    Map delete(String id);
}

package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Album;

import java.util.Map;

public interface AlbumService {
    Map findAll(Integer page,Integer rows);
    Map add(Album album);
    Map update(Album album);
    Map delete(String id);
}

package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Album;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AlbumDao extends Mapper<Album> {
    List<Album> findByOrder();
}

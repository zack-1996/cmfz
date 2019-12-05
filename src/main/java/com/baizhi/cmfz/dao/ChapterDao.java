package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Chapter;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ChapterDao extends Mapper<Chapter> {
    @Select("select * from chapter where album_id = #{id}")
    List<Chapter> findByAlbumId(String id);
}

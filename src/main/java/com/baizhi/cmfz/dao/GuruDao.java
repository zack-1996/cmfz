package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Guru;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GuruDao extends Mapper<Guru> {
    List<Guru> findByGZ(String uid);
}

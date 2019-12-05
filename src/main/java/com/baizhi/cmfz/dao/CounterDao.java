package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Counter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CounterDao extends Mapper<Counter> {
    @Select("select * from counter where user_id=#{user_id} and course_id=#{course_id}")
    List<Counter> findByUidAndCid(@Param("user_id") String uid,@Param("course_id") String cid);
}

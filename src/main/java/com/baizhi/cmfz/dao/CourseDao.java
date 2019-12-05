package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Course;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CourseDao extends Mapper<Course> {
    @Select("select * from course where user_id = #{user_id}")
    List<Course> findByUserId(String user_id);
}

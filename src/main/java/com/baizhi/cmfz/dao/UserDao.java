package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.MapVO;
import com.baizhi.cmfz.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User> {
    Integer findCountSexAndDay(@Param("sex")String sex,@Param("day") Integer day);
    List<MapVO> findByLocation();
}

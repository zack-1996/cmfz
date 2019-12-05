package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.GuanZhu;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GuanZhuDao extends Mapper<GuanZhu> {
    @Select("insert into guanZhu values(#{id},#{user_id},#{guru_id})")
    void addGuanZhu(GuanZhu guanZhu);
    @Select("select * from guanZhu where user_id=#{uid}")
    List<GuanZhu> findByUid(String uid);
}

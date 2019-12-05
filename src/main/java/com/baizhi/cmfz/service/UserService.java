package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.MapVO;
import com.baizhi.cmfz.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map login(String phone,String password);
    Map findAll(Integer page, Integer rows);
    Map add(User user);
    Map update(User user);
    Map delete(String id);
    Map findUserCount();
    List<MapVO> findByLocation();
}

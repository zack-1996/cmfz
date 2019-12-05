package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {
    List<Banner> selectAll(Integer page,Integer rows);
    Integer selectCount();
    Map add(Banner banner);
    Map update(Banner banner);
    Map delete(String id);
}

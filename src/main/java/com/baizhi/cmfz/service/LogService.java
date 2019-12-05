package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Log;

import java.util.List;

public interface LogService {
    List<Log> findAll();
    void add(Log log);
}

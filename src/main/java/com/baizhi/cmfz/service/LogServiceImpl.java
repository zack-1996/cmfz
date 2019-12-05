package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.LogDao;
import com.baizhi.cmfz.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Log> findAll() {
        List<Log> logs = logDao.selectAll();
        return logs;
    }

    @Override
    public void add(Log log) {
        logDao.insert(log);

    }
}

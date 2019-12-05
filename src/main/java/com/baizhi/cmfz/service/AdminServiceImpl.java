package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.AdminDao;
import com.baizhi.cmfz.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public Admin login(String username, String password) {
        Admin admin = adminDao.login(username, password);
        return admin;
    }
}

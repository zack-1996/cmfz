package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Admin;

public interface AdminService {
    Admin login(String username,String password);
}

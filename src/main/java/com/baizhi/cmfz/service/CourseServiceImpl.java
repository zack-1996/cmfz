package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.CourseDao;
import com.baizhi.cmfz.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;
    @Override
    public Map addCourse(String uid, String title) {
        HashMap hashMap=new HashMap();
        try {
            Course course=new Course().setId(UUID.randomUUID().toString()).setCreate_date(new Date()).setTitle(title).setUser_id(uid).setType("2");
            courseDao.insertSelective(course);
            List<Course> courses = courseDao.findByUserId(uid);
            hashMap.put("status","200");
            hashMap.put("courses",courses);
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","添加功课失败");
        }finally {
            return hashMap;
        }


    }

    @Override
    public Map deleteCourse(String uid, String id) {
        HashMap hashMap=new HashMap();
        try {
            courseDao.deleteByPrimaryKey(new Course().setId(id));
            List<Course> courses = courseDao.findByUserId(uid);
            hashMap.put("status","200");
            hashMap.put("courses",courses);
        }catch (Exception e){
            hashMap.put("status","-200");
            hashMap.put("message","添加功课失败");
        }finally {
        return hashMap;
         }

    }
}

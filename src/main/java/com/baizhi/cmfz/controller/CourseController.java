package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @RequestMapping("add")
    public Map add(String uid,String title){
        Map map = courseService.addCourse(uid, title);
        return map;
    }
    @RequestMapping("delete")
    public Map delete(String uid,String id){
        Map map = courseService.deleteCourse(uid, id);
        return map;
    }
}

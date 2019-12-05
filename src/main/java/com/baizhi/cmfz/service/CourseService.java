package com.baizhi.cmfz.service;

import java.util.Map;

public interface CourseService {
    Map addCourse(String uid,String title);
    Map deleteCourse(String uid,String id);
}

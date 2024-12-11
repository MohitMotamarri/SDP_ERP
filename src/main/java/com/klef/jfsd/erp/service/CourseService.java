package com.klef.jfsd.erp.service;

import java.util.List;

import com.klef.jfsd.erp.model.Course;

public interface CourseService {

	void addCourse(Course course);

	List<Course> getAllCourses();
    
}

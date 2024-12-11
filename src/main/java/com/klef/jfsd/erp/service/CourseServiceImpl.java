package com.klef.jfsd.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.erp.model.Course;
import com.klef.jfsd.erp.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService
{
	
	@Autowired
    private CourseRepository courseRepository;

    @Override
    public void addCourse(Course course) {
        courseRepository.save(course);
    }

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}
}

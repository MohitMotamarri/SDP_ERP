package com.klef.jfsd.erp.service;

import java.util.List;

import com.klef.jfsd.erp.DTO.LoginRequest;
import com.klef.jfsd.erp.model.Course;
import com.klef.jfsd.erp.model.Student;

public interface StudentService {

	List<Course> viewCoursesForStudent(Long studentId);

	Student checkStudentLogin(LoginRequest request);

	boolean registerStudentForCourse(Long studentId, Long courseId);
	 
}

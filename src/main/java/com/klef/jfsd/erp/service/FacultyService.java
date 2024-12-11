package com.klef.jfsd.erp.service;

import java.util.List;

import com.klef.jfsd.erp.DTO.LoginRequest;
import com.klef.jfsd.erp.model.Course;
import com.klef.jfsd.erp.model.Faculty;
import com.klef.jfsd.erp.model.FacultyLeave;
import com.klef.jfsd.erp.model.Student;

public interface FacultyService 
{
	List<Student> viewAllStudentsForFaculty();
	Student viewStudentById(Long id);
	List<Course> viewCoursesForFaculty(Long facultyId);
	Faculty checkFacultyLogin(LoginRequest request);
	
    public Faculty viewFacultyById(Long id);


	//
	public String applyLeave(FacultyLeave facultyLeave);
	  public FacultyLeave updateStatusLeave(int leaveid,String status);
	  public List<FacultyLeave>displayAllLeaves();
	  public List<FacultyLeave>viewStatusByFaculty(long fid);
}
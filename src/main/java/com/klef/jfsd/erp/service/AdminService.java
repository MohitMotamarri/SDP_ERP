package com.klef.jfsd.erp.service;

import com.klef.jfsd.erp.model.Student;
import com.klef.jfsd.erp.DTO.CourseMappingDTO;
import com.klef.jfsd.erp.DTO.LoginRequest;
import com.klef.jfsd.erp.model.Admin;
import com.klef.jfsd.erp.model.Faculty;
import java.util.List;

public interface AdminService {
    // Existing methods
    void addStudent(Student student);
    void addFaculty(Faculty faculty);

    // New methods
    List<Student> viewAllStudents();
    List<Faculty> viewAllFaculty();
    public Student viewStudentById(Long id);
    public Faculty viewFacultyById(Long id);
    void mapCourseToStudent(CourseMappingDTO courseMappingDTO);
	void mapCourseToFaculty(Long facultyId, Long courseId);
	void updateStudent(Student updatedStudent);
	void updateFaculty(Faculty updatedFaculty);
	public Admin checkAdminLogin(LoginRequest request);
	void deleteStudentById(Long id);
	void deleteFacultyById(Long id);
	void deleteCourseById(Long id);
	
    
    

}

package com.klef.jfsd.erp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.erp.DTO.LoginRequest;
import com.klef.jfsd.erp.model.Course;
import com.klef.jfsd.erp.model.Faculty;
import com.klef.jfsd.erp.model.Student;
import com.klef.jfsd.erp.repository.CourseRepository;
import com.klef.jfsd.erp.repository.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	 private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Course> viewCoursesForStudent(Long studentId) {
		 Student student = studentRepository.findById(studentId)
		            .orElseThrow(() -> new RuntimeException("student not found with ID: " + studentId));
		    
		    return new ArrayList<>(student.getCourses()); // Returning the list of courses
	}
	public Student checkStudentLogin(LoginRequest request) {
        // Retrieve student by id and password from the repository
        return studentRepository.findByIdAndPassword(request.getId(), request.getPassword());
    }
	
	public boolean registerStudentForCourse(Long studentId, Long courseId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            Student student = studentOpt.get();
            Course course = courseOpt.get();

            student.getCourses().add(course); // Assuming Many-to-Many relationship
            studentRepository.save(student);
            return true;
        }
        return false;
    }
	
}

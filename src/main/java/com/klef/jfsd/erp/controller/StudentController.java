package com.klef.jfsd.erp.controller;

import com.klef.jfsd.erp.DTO.CourseMappingDTO;
import com.klef.jfsd.erp.DTO.LoginRequest;
import com.klef.jfsd.erp.model.Course;
import com.klef.jfsd.erp.model.Student;
import com.klef.jfsd.erp.service.CourseService;
import com.klef.jfsd.erp.service.StudentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
	
	@Autowired
	private CourseService courseService;

    @Autowired
    private StudentService studentService;
    
    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<Course>> getCoursesForStudent(@PathVariable Long studentId) {
        List<Course> courses = studentService.viewCoursesForStudent(studentId);
        return ResponseEntity.ok(courses);
    }

    
    // Endpoint to fetch all courses
    @GetMapping("/getallcourses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    
    @PostMapping("/checkStudentLogin")
    public ResponseEntity<Student> checkStudentLogin(@RequestBody LoginRequest request) {
        // Check student login using the id and password
        Student student = studentService.checkStudentLogin(request);
        
        if (student != null) {
            // If login is successful, return student data
            return ResponseEntity.ok(student);
        } else {
            // If login fails, return an error response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(null);
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerForCourse(@RequestBody CourseMappingDTO courseMappingDTO) {
        boolean success = studentService.registerStudentForCourse(courseMappingDTO.getStudentId(), courseMappingDTO.getCourseId());
        if (success) {
            return ResponseEntity.ok("Student successfully registered for the course!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed!");
        }
    }
    
    
}

package com.klef.jfsd.erp.controller;

import com.klef.jfsd.erp.DTO.CourseMappingDTO;
import com.klef.jfsd.erp.DTO.CourseMappingFac;
import com.klef.jfsd.erp.DTO.LoginRequest;
import com.klef.jfsd.erp.model.Admin;
import com.klef.jfsd.erp.model.Course;
import com.klef.jfsd.erp.model.Faculty;
import com.klef.jfsd.erp.model.Student;
import com.klef.jfsd.erp.service.AdminService;
import com.klef.jfsd.erp.service.CourseService;
import com.klef.jfsd.erp.service.FacultyService;
import com.klef.jfsd.erp.service.StudentService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private FacultyService facultyService;
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private CourseService courseService;
    

    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(
        @RequestParam("studentName") String studentName,
        @RequestParam("studentGender") String studentGender,
        @RequestParam("studentFatherName") String studentFatherName,
        @RequestParam("studentMotherName") String studentMotherName,
        @RequestParam("studentFatherContact") String studentFatherContact,
        @RequestParam("studentMotherContact") String studentMotherContact,
        @RequestParam("studentDateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate studentDateOfBirth,
        @RequestParam("studentPersonalEmail") String studentPersonalEmail,
        @RequestParam("studentIdentification") String studentIdentification,
        @RequestParam("studentMajorDegree") String studentMajorDegree,
        @RequestParam("studentProgram") String studentProgram,
        @RequestParam("studentAddressContact") String studentAddressContact,
        @RequestParam("photo") MultipartFile photo
    ) {
        try {
            // Create a new Student object and set its fields
            Student student = new Student();
            student.setName(studentName);
            student.setGender(studentGender);
            student.setFatherName(studentFatherName);
            student.setMotherName(studentMotherName);
            student.setFatherContact(studentFatherContact);
            student.setMotherContact(studentMotherContact);
            student.setDateOfBirth(java.sql.Date.valueOf(studentDateOfBirth)); // Convert LocalDate to SQL Date
            student.setPersonalEmail(studentPersonalEmail);
            student.setIdentification(studentIdentification);
            student.setMajorDegree(studentMajorDegree);
            student.setProgram(studentProgram);
            student.setAddressContact(studentAddressContact);
            student.setPassword(studentName);

            // Convert photo to byte array and set it in the student object
            if (photo != null && !photo.isEmpty()) {
                student.setPhoto(photo.getBytes());
            }

            // Save the student object using the service
            adminService.addStudent(student);

            return ResponseEntity.ok("Student Added Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add student: " + e.getMessage());
        }
    }
    @PostMapping("/addFaculty")
    public ResponseEntity<?> addFaculty(
        @RequestParam("facultyName") String facultyName,
        @RequestParam("facultyGender") String facultyGender,
        @RequestParam("facultyDateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate facultyDateOfBirth,
        @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
        @RequestParam("facultyIdentification") String facultyIdentification,
        @RequestParam("facultyDepartment") String facultyDepartment,
        @RequestParam("facultyPosition") String facultyPosition,
        @RequestParam("facultyAddressContact") String facultyAddressContact,
        @RequestParam("facultyPhoto") MultipartFile facultyPhoto,
        @RequestParam("facultyPassword") String password
    ) {
        try {
            // Create a new Faculty object and set its fields
            Faculty faculty = new Faculty();
            faculty.setFacultyName(facultyName);
            faculty.setGender(facultyGender);
            faculty.setDateOfBirth(java.sql.Date.valueOf(facultyDateOfBirth)); // Convert LocalDate to SQL Date
            faculty.setPersonalEmail(facultyPersonalEmail);
            faculty.setIdentification(facultyIdentification);
            faculty.setDepartment(facultyDepartment);
            faculty.setPosition(facultyPosition);
            faculty.setAddressContact(facultyAddressContact);
            faculty.setPassword(password); // Set password

            // Convert photo to byte array and set it in the faculty object
            if (facultyPhoto != null && !facultyPhoto.isEmpty()) {
                faculty.setFacultyPhoto(facultyPhoto.getBytes());
            }

            // Save the faculty object using the service
            adminService.addFaculty(faculty);

            return ResponseEntity.ok("Faculty Added Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add faculty: " + e.getMessage());
        }
    }

    
    // New endpoint to view all students
    @GetMapping("/viewAllStudents")
    public ResponseEntity<List<Student>> viewAllStudents() {
        List<Student> students = adminService.viewAllStudents();
//        System.out.println(students);
        return ResponseEntity.ok(students);
    }

    // New endpoint to view all faculty
    @GetMapping("/viewAllFaculty")
    public ResponseEntity<List<Faculty>> viewAllFaculty() {
        List<Faculty> faculties = adminService.viewAllFaculty();
        return ResponseEntity.ok(faculties);
    }
    
    @GetMapping("/viewStudent/{id}")
    public ResponseEntity<Student> viewStudentById(@PathVariable Long id) {
        Student student = adminService.viewStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/viewFaculty/{id}")
    public ResponseEntity<Faculty> viewFacultyById(@PathVariable Long id) {
        Faculty faculty = adminService.viewFacultyById(id);
        return ResponseEntity.ok(faculty);
    }
    
    
    @PostMapping("/addCourse")
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
        return ResponseEntity.ok("Course added successfully");
    }
    
    @PostMapping("/mapCourseToStudent")
    public ResponseEntity<String> mapCourseToStudent(@RequestBody CourseMappingDTO courseMappingDTO) {
        adminService.mapCourseToStudent(courseMappingDTO);
        return ResponseEntity.ok("Course mapped to student successfully");
    }
    
 // Mapping course to faculty using CourseMappingFac DTO
    @PostMapping("/mapCourseToFaculty")
    public ResponseEntity<String> mapCourseToFaculty(@RequestBody CourseMappingFac courseMappingFac) {
        adminService.mapCourseToFaculty(courseMappingFac.getFacultyId(), courseMappingFac.getCourseId());
        return ResponseEntity.ok("Course mapped to faculty successfully");
    }
    
    
    @PutMapping("/updateStudent")
    public ResponseEntity<?> updateStudent(
            @RequestParam("studentId") Long studentId,
            @RequestParam("studentName") String studentName,
            @RequestParam("studentGender") String studentGender,
            @RequestParam("studentFatherName") String studentFatherName,
            @RequestParam("studentMotherName") String studentMotherName,
            @RequestParam("studentFatherContact") String studentFatherContact,
            @RequestParam("studentMotherContact") String studentMotherContact,
            @RequestParam("studentDateOfBirth") String studentDateOfBirth,
            @RequestParam("studentPersonalEmail") String studentPersonalEmail,
            @RequestParam("studentIdentification") String studentIdentification,
            @RequestParam("studentMajorDegree") String studentMajorDegree,
            @RequestParam("studentProgram") String studentProgram,
            @RequestParam("studentAddressContact") String studentAddressContact,
            @RequestParam("studentPassword") String studentPassword,
            @RequestParam(value = "photo", required = false) MultipartFile photo
    ) {
        try {
            // Create a new Student object and set its fields
            Student updatedStudent = new Student();
            updatedStudent.setId(studentId);
            updatedStudent.setName(studentName);
            updatedStudent.setGender(studentGender);
            updatedStudent.setFatherName(studentFatherName);
            updatedStudent.setMotherName(studentMotherName);
            updatedStudent.setFatherContact(studentFatherContact);
            updatedStudent.setMotherContact(studentMotherContact);

            // Handle the date conversion
            try {
                updatedStudent.setDateOfBirth(java.sql.Date.valueOf(studentDateOfBirth)); // Convert String to SQL Date
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Invalid date format. Please use yyyy-MM-dd.");
            }

            updatedStudent.setPersonalEmail(studentPersonalEmail);
            updatedStudent.setIdentification(studentIdentification);
            updatedStudent.setMajorDegree(studentMajorDegree);
            updatedStudent.setProgram(studentProgram);
            updatedStudent.setAddressContact(studentAddressContact);
            updatedStudent.setPassword(studentPassword);

            // Handle photo file if available
            if (photo != null && !photo.isEmpty()) {
                updatedStudent.setPhoto(photo.getBytes());
            }

            // Update the student object using the service
            adminService.updateStudent(updatedStudent);

            // Return success response
            return ResponseEntity.ok("Student updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update student: " + e.getMessage());
        }
    }


    
    @PutMapping("/updateFaculty")
    public ResponseEntity<String> updateFaculty(@RequestBody Faculty updatedFaculty) {
        adminService.updateFaculty(updatedFaculty);
        return ResponseEntity.ok("Faculty updated successfully");
    }
    
    
    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        adminService.deleteStudentById(id);
        return ResponseEntity.ok("Student deleted successfully.");
    }
    
    
    @DeleteMapping("/delete-faculty/{id}")
    public ResponseEntity<String> deleteFacultyById(@PathVariable Long id) {
        adminService.deleteFacultyById(id);
        return ResponseEntity.ok("Faculty deleted successfully.");
    }
    
    @DeleteMapping("/delete-course/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Long id) {
        adminService.deleteCourseById(id);
        return ResponseEntity.ok("Course deleted successfully.");
    }
    
    ///login check
    
    @PostMapping("/checkadminLogin")
    public ResponseEntity<?> checkAdminLogin(@RequestBody LoginRequest request) {
        Admin admin = adminService.checkAdminLogin(request);
        if (admin != null) 
        {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("role", "admin");  // Include the role
            return ResponseEntity.ok(response); // Return the response with role
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Login Details");
        }
    }


    
}

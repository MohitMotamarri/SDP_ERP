package com.klef.jfsd.erp.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.klef.jfsd.erp.DTO.CourseMappingDTO;
import com.klef.jfsd.erp.DTO.LoginRequest;
import com.klef.jfsd.erp.model.Admin;
import com.klef.jfsd.erp.model.Course;
import com.klef.jfsd.erp.model.Faculty;
import com.klef.jfsd.erp.model.Student;
import com.klef.jfsd.erp.repository.AdminRepository;
import com.klef.jfsd.erp.repository.CourseRepository;
import com.klef.jfsd.erp.repository.FacultyRepository;
import com.klef.jfsd.erp.repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private FacultyRepository facultyRepository;
    

	@Override
	public void addFaculty(Faculty faculty) {
		
		facultyRepository.save(faculty);
	}
	
	

	@Override
	public void addStudent(Student student) {
		studentRepository.save(student);
		
	}
	
	// New methods
    @Override
    public List<Student> viewAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Faculty> viewAllFaculty() {
        return facultyRepository.findAll();
    }
    
    public Student viewStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Student not found"));
    }

    public Faculty viewFacultyById(Long id) {
        return facultyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Faculty not found"));
    }
    
    
    @Override
    public void mapCourseToStudent(CourseMappingDTO courseMappingDTO) {
        // Retrieve the student by ID
        Student student = studentRepository.findById(courseMappingDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + courseMappingDTO.getStudentId()));

        // Retrieve the course by ID
        Course course = courseRepository.findById(courseMappingDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseMappingDTO.getCourseId()));

        // Add the course to the student's list of courses
        student.getCourses().add(course);

        // Save the updated student back to the repository
        studentRepository.save(student);
    }
    
    
    @Override
    public void mapCourseToFaculty(Long facultyId, Long courseId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found with ID: " + facultyId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

        faculty.getCourses().add(course);  // Add the course to the faculty's courses set
        facultyRepository.save(faculty);   // Save the updated faculty
    }
    
    
    @Transactional
    public void updateStudent(Student updatedStudent) {
        // Check if the student exists in the database
        Student existingStudent = studentRepository.findById(updatedStudent.getId())
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + updatedStudent.getId() + " not found"));

        // Update the fields if they are provided in the updated student
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setGender(updatedStudent.getGender());
        existingStudent.setFatherName(updatedStudent.getFatherName());
        existingStudent.setMotherName(updatedStudent.getMotherName());
        existingStudent.setFatherContact(updatedStudent.getFatherContact());
        existingStudent.setMotherContact(updatedStudent.getMotherContact());
        existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
        existingStudent.setPersonalEmail(updatedStudent.getPersonalEmail());
        existingStudent.setIdentification(updatedStudent.getIdentification());
        existingStudent.setMajorDegree(updatedStudent.getMajorDegree());
        existingStudent.setProgram(updatedStudent.getProgram());
        existingStudent.setAddressContact(updatedStudent.getAddressContact());
        existingStudent.setPassword(updatedStudent.getPassword());

        // Update the photo if it's provided and not empty
        if (updatedStudent.getPhoto() != null && updatedStudent.getPhoto().length > 0) {
            existingStudent.setPhoto(updatedStudent.getPhoto());
        }

        // Save the updated student back to the database
        studentRepository.save(existingStudent);
    }

    
    @Override
    public void updateFaculty(Faculty updatedFaculty) {
        Faculty existingFaculty = facultyRepository.findById(updatedFaculty.getId())
                .orElseThrow(() -> new RuntimeException("Faculty not found with ID: " + updatedFaculty.getId()));

        // Update the fields
        existingFaculty.setFacultyName(updatedFaculty.getFacultyName());
        existingFaculty.setGender(updatedFaculty.getGender());
        existingFaculty.setDateOfBirth(updatedFaculty.getDateOfBirth());
        existingFaculty.setPersonalEmail(updatedFaculty.getPersonalEmail());
        existingFaculty.setIdentification(updatedFaculty.getIdentification());
        existingFaculty.setDepartment(updatedFaculty.getDepartment());
        existingFaculty.setPosition(updatedFaculty.getPosition());
        existingFaculty.setAddressContact(updatedFaculty.getAddressContact());

        // Save the updated faculty
        facultyRepository.save(existingFaculty);
    }
    
    @Override
    public Admin checkAdminLogin(LoginRequest request) {
        // Modify to check by id and password
        Admin admin = adminRepository.findByIdAndPassword(request.getId(), request.getPassword());
        return admin;
    }
    
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student with ID " + id + " not found.");
        }
        studentRepository.deleteById(id);
    }

    public void deleteFacultyById(Long id) {
        if (!facultyRepository.existsById(id)) {
            throw new RuntimeException("Faculty with ID " + id + " not found.");
        }
        facultyRepository.deleteById(id);
    }

    public void deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course with ID " + id + " not found.");
        }
        courseRepository.deleteById(id);
    }

}

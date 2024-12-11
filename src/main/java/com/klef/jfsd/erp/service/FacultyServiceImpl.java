package com.klef.jfsd.erp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.erp.DTO.LoginRequest;
import com.klef.jfsd.erp.model.Course;
import com.klef.jfsd.erp.model.Faculty;
import com.klef.jfsd.erp.model.FacultyLeave;
import com.klef.jfsd.erp.model.Student;
import com.klef.jfsd.erp.repository.FacultyLeaveRepository;
import com.klef.jfsd.erp.repository.FacultyRepository;
import com.klef.jfsd.erp.repository.StudentRepository;


@Service
public class FacultyServiceImpl implements FacultyService
{
	
	   @Autowired
	   private FacultyRepository facultyRepository;
	   
	   @Autowired
	   private StudentRepository studentRepository;
	   
	   @Autowired
	   private FacultyLeaveRepository facultyLeaveRepository;

	@Override
	public List<Student> viewAllStudentsForFaculty() {
		return studentRepository.findAll();
	}
	
	@Override
	public Student viewStudentById(Long id) {
		return studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
	}
	
	
	
	
	@Override
	public List<Course> viewCoursesForFaculty(Long facultyId) {
	    Faculty faculty = facultyRepository.findById(facultyId)
	            .orElseThrow(() -> new RuntimeException("Faculty not found with ID: " + facultyId));
	    
	    return new ArrayList<>(faculty.getCourses()); // Returning the list of courses
	}
	
	public Faculty checkFacultyLogin(LoginRequest request) {
        // Retrieve faculty by id and password from the repository
        return facultyRepository.findByIdAndPassword(request.getId(), request.getPassword());
    }
	
	 //
	@Override
    public String applyLeave(FacultyLeave facultyLeave) {
        facultyLeave.setStatus("Pending");
         facultyLeaveRepository.save(facultyLeave);
         return "Faculty Leave Sent Successfully";
    }

    @Override
    public List<FacultyLeave> displayAllLeaves() {
        return facultyLeaveRepository.findAll();
    }

    @Override
    public List<FacultyLeave> viewStatusByFaculty(long fid) {
        Faculty faculty = facultyRepository.findById(fid)
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + fid));
        return facultyLeaveRepository.findByFaculty(faculty);
    }

    @Override
    public FacultyLeave updateStatusLeave(int leaveId, String status) {
        FacultyLeave leaveRequest = facultyLeaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave request not found with id: " + leaveId));
        leaveRequest.setStatus(status);
        return facultyLeaveRepository.save(leaveRequest);
    }

	   
    public Faculty viewFacultyById(Long id) {
        return facultyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Faculty not found"));
    }
    
}



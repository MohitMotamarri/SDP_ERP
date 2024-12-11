package com.klef.jfsd.erp.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.jfsd.erp.DTO.LoginRequest;
import com.klef.jfsd.erp.model.Course;
import com.klef.jfsd.erp.model.Faculty;
import com.klef.jfsd.erp.model.FacultyLeave;
import com.klef.jfsd.erp.model.Student;
import com.klef.jfsd.erp.service.FacultyService;

@RestController
@RequestMapping("/faculty")
@CrossOrigin
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/viewAllStudents")
    public ResponseEntity<List<Student>> viewAllStudents() {
        List<Student> students = facultyService.viewAllStudentsForFaculty();
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/viewStudent/{id}")
    public ResponseEntity<Student> viewStudentById(@PathVariable Long id) {
        Student student = facultyService.viewStudentById(id);
        return ResponseEntity.ok(student);
    }
    
   
    
    @GetMapping("/viewCourses")
    public ResponseEntity<List<Course>> viewAllCoursesForFaculty(@RequestParam Long facultyId) {
        List<Course> courses = facultyService.viewCoursesForFaculty(facultyId);
        return ResponseEntity.ok(courses);
    }
    
    @PostMapping("/checkFacultyLogin")
    public ResponseEntity<Faculty> checkFacultyLogin(@RequestBody LoginRequest request) {
        // Check faculty login using the id and password
        Faculty faculty = facultyService.checkFacultyLogin(request);
        
        if (faculty != null) {
            // If login is successful, return faculty data
            return ResponseEntity.ok(faculty);
        } else {
            // If login fails, return an error response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(null);
        }
    }

 //faculty leave   
    @PostMapping("leave/apply")
    public ResponseEntity<String> applyLeave(@RequestBody Map<String, Object> leaveDetails) {
        try {
          if (!leaveDetails.containsKey("facultyId")) {
                return ResponseEntity.badRequest().body("Faculty ID is required.");
            }
            // Extracting input data from the request body
            long facultyId = Integer.parseInt(leaveDetails.get("facultyId").toString());
            String startDateStr = leaveDetails.get("startDate").toString();
            String endDateStr = leaveDetails.get("endDate").toString();
            String reason = leaveDetails.get("reason").toString();

            // Parsing date strings to java.util.Date
            java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
            java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);

            // Validating and creating a FacultyLeave object
            FacultyLeave leave = new FacultyLeave();
            leave.setFaculty(facultyService.viewFacultyById(facultyId));
            leave.setStartDate(startDate);
            leave.setEndDate(endDate);
            leave.setReason(reason);
            leave.setStatus("Pending"); // Default status when applying leave

            // Save the leave request using the service
            String result = facultyService.applyLeave(leave);

            // Return success response
            return ResponseEntity.ok(result);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid faculty ID: " + e.getMessage());
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Use yyyy-MM-dd: " + e.getMessage());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Missing required fields: " + e.getMessage());
        }
    }


    // Getting leave requests for admin
    @GetMapping("/appliedforleave")
      public ResponseEntity<List<FacultyLeave>> getAllLeaves() {
          List<FacultyLeave> leaves = facultyService.displayAllLeaves();
          return ResponseEntity.ok(leaves);
      }


    // Updating leave status for admin
    @PutMapping("/update/{leaveId}")
      public ResponseEntity<FacultyLeave> updateLeaveStatus(@PathVariable int leaveId, @RequestParam String status) {
          FacultyLeave updatedLeave = facultyService.updateStatusLeave(leaveId, status);
          return ResponseEntity.ok(updatedLeave);
      }
    @GetMapping("/{facultyId}")
      public ResponseEntity<List<FacultyLeave>> getLeavesByFaculty(@PathVariable int facultyId) {
          List<FacultyLeave> leaves = facultyService.viewStatusByFaculty(facultyId);
          if(!leaves.isEmpty())
          {
            return ResponseEntity.ok(leaves);          
          }
          else
          {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
          }
      }
    
 

}

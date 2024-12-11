package com.klef.jfsd.erp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.jfsd.erp.model.Faculty;
import com.klef.jfsd.erp.model.FacultyLeave;



public interface FacultyLeaveRepository extends JpaRepository<FacultyLeave, Integer>
{
  List<FacultyLeave> findByFaculty(Faculty faculty);
}

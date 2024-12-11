package com.klef.jfsd.erp.repository;

import com.klef.jfsd.erp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByIdAndPassword(Long id, String password);
    // Add custom query methods if needed
}

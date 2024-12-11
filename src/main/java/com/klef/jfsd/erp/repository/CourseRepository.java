package com.klef.jfsd.erp.repository;

import com.klef.jfsd.erp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // Add custom query methods if needed
}

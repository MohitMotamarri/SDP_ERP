package com.klef.jfsd.erp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.jfsd.erp.model.Faculty;


public interface FacultyRepository extends JpaRepository<Faculty, Long> {

	Faculty findByIdAndPassword(Long id, String password);
    // Add custom query methods if needed

	Optional<Faculty> findById(Long id);
}

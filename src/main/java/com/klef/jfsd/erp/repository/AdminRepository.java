package com.klef.jfsd.erp.repository;

import com.klef.jfsd.erp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Admin findByUsernameAndPassword(String username, String password);
	Admin findByIdAndPassword(Long id, String password);
    
}

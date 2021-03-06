package com.example.demo.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.EmployeeDetails;

@Repository
public interface EmployeeDetailsRepository extends MongoRepository<EmployeeDetails, String> {
	
	// FOR GETTING ADMIN CONTACT DETAILS
	 @Query(value="{ isAdmin: 1}")
	 List<EmployeeDetails> findByIsAdmin();

}

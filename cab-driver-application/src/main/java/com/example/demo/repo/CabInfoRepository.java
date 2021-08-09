package com.example.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.entity.CabInfo;



public interface CabInfoRepository extends MongoRepository<CabInfo, String>{
	
	// FOR GETTING DRIVER PROFILE
	CabInfo findByCabNumber(String cabNumber);
}

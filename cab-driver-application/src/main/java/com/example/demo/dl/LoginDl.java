package com.example.demo.dl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.entity.CabInfo;
import com.example.demo.repo.CabInfoRepository;

@Component
public class LoginDl {

	@Autowired
	private CabInfoRepository cabInfoRepo;

	//AUTHENTICATE CREDENTIALS
	public CabInfo findByCabNumber(String cabNumber) {
		return this.cabInfoRepo.findByCabNumber(cabNumber);
	}

	//GETTING CABINFO BASED ON CAB NUMBER
	public Optional<CabInfo> findById(String cabNumber) {
		
		return this.cabInfoRepo.findById(cabNumber);
	}

}
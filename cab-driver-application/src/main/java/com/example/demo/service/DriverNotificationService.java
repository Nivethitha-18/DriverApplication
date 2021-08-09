package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.CabInfo;
import com.example.demo.entity.EmployeeDetails;
import com.example.demo.entity.TripCabInfo;
import com.example.demo.repo.CabInfoRepository;
import com.example.demo.repo.DriverNotificationRepository;
import com.example.demo.repo.EmployeeDetailsRepository;

@Service
public class DriverNotificationService {

	@Autowired
	public DriverNotificationRepository repofordrivernotification;

	@Autowired
	public CabInfoRepository cabInfoRepo;

	@Autowired
	public EmployeeDetailsRepository employeedetailsrepo;

	// FOR GETTING DRIVER PROFILE
	public CabInfo findByCabNumber(String cabNumber) {
		CabInfo cabinfo = this.cabInfoRepo.findByCabNumber(cabNumber);
		return cabinfo;

	}

	// FOR  GETTING DRIVER'S NOTIFICATION
	public TripCabInfo getTripAssignedDetailsByCabNumber(String cabNumber) {
		TripCabInfo tripcabinfo = this.repofordrivernotification.getTripAssignedDetailsByCabNumber(cabNumber);
		return tripcabinfo;

	}
	
	// FOR GETTING ADMIN CONTACT DETAILS
	public List<EmployeeDetails> findByIsAdmin() {
		List<EmployeeDetails> isAdmin = this.employeedetailsrepo.findByIsAdmin();
		return isAdmin;

	}
}

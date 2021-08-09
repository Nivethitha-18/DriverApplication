package com.example.demo.bl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.entity.CabInfo;
import com.example.demo.entity.EmployeeDetails;
import com.example.demo.entity.TripCabInfo;
import com.example.demo.service.DriverNotificationService;

@Component
public class DriverNotificationBL {

	@Autowired
	DriverNotificationService notificationservice;

	// FOR GETTING DRIVER'S NOTIFICATION
	public TripCabInfo getTripAssignedDetailsByCabNumber(String cabNumber) {

		return this.notificationservice.getTripAssignedDetailsByCabNumber(cabNumber);
	}

	// FOR  GETTING DRIVER PROFILES
	public CabInfo findByCabNumber(String cabNumber) {

		return this.notificationservice.findByCabNumber(cabNumber);
	}

	// FOR GETTING ADMIN CONTACT DETAILS
	public List<EmployeeDetails> findByIsAdmin() {

		return this.notificationservice.findByIsAdmin();
	}

}

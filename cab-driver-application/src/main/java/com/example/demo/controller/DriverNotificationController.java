package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bl.DriverNotificationBL;
import com.example.demo.entity.CabInfo;
import com.example.demo.entity.EmployeeDetails;
import com.example.demo.entity.TripCabInfo;


@CrossOrigin(origins = { "*" })
@RestController
public class DriverNotificationController {

	@Autowired
	private DriverNotificationBL notificationbl;

	// FOR GETTING DRIVER'S NOTIFICATION

	@GetMapping(path = "/notification/{cabNumber}")
	public TripCabInfo getNotificationByCabNumber(@PathVariable String cabNumber) {
		TripCabInfo tripObj = this.notificationbl.getTripAssignedDetailsByCabNumber(cabNumber);
		return tripObj;
	}

	// FOR  GETTING DRIVER PROFILES

	@GetMapping(path = "/driverProfile/{cabNumber}")
	public CabInfo getProfileByCabNumber(@PathVariable String cabNumber) {
		CabInfo driverprofile = this.notificationbl.findByCabNumber(cabNumber);
		return driverprofile;
	}

	// FOR GETTING ADMIN CONTACT DETAILS

	@GetMapping(path = "/adminContactDetails")
	public List<EmployeeDetails> getAdminContacts() {
		List<EmployeeDetails> admincontact = this.notificationbl.findByIsAdmin();
		return admincontact;

	}

}

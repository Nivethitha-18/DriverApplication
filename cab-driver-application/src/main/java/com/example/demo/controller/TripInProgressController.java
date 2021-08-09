package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.bl.InProgressBL;
import com.example.demo.entity.BookingRequest;
import com.example.demo.entity.TripCabInfo;


@CrossOrigin(origins = { "*" })
@RestController
public class TripInProgressController {

	@Autowired
	private InProgressBL inprogressbl;

	//GETTING EMPLOYEE DETAILS BASED ON SHOW STATUS
	@GetMapping(path = "/bookings/status/{TripId}")
	public ResponseEntity<List<BookingRequest>> getFilteredBookings(@PathVariable("TripId") long tripId) {

		List<BookingRequest> requests = this.inprogressbl.findShowusers(tripId).get();

		return ResponseEntity.status(HttpStatus.OK).body(requests);
	}

	// FOR UPDATING STATUS OF EMPLOYEE
	@PutMapping(path = "/employee/status/{employeeID}")
	public ResponseEntity<BookingRequest> storeEmployeeStatus(@PathVariable("employeeID") String employeeID) {
		BookingRequest savedStatus = this.inprogressbl.storeEmployeeStatus(employeeID);
		return ResponseEntity.status(HttpStatus.OK).body(savedStatus);
	}

	// FOR UPDATING END STATUS OF CAB
	@PutMapping("/updateme/{tripCabId}")
	public ResponseEntity<TripCabInfo> updatebytripCabID(@PathVariable("tripCabId") long tripCabID) {

		TripCabInfo savedStatus = this.inprogressbl.updateTrip(tripCabID);

		return ResponseEntity.status(HttpStatus.OK).body(savedStatus);
	}

	// FOR GETTING SERVER TIME FOR START TIME
	@GetMapping(path = "getServerTime/{tripCabID}")
	public TripCabInfo getBookingTime(@PathVariable("tripCabID") long tripCabID) {
		return this.inprogressbl.getBookingTime(tripCabID);

	}

}

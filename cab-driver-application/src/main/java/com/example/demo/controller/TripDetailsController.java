package com.example.demo.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bl.TripDetailsBL;
import com.example.demo.entity.BookingRequest;
import com.example.demo.entity.TripCabInfo;
import com.example.demo.repo.BookingRequestRepository;


@CrossOrigin(origins = { "*" })
@RestController
public class TripDetailsController {

	@Autowired
	private TripDetailsBL tripdetailsbl;

	@Autowired
	private BookingRequestRepository repo;

	
   //GETTING TRIP DETAILS BASED ON TRIP ID
	@GetMapping(path = "/Getalldetails/{TripId}")
	public ResponseEntity<List<BookingRequest>> getbytripid(@PathVariable("TripId") long srchid) {
		Optional<List<BookingRequest>> book = this.tripdetailsbl.findByTripCabId(srchid);
		TripCabInfo trip = this.tripdetailsbl.findTripCabInfo(srchid);
		if (trip.getStartTime() != null) {
			return ResponseEntity.status(261).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(book.get());

	}

	
//	@GetMapping(path = "/Address/")
//	public List<BookingRequest> getbytripid1(@RequestParam(value = "TripId") long srchid) {
//		Optional<List<BookingRequest>> book = this.tripdetailsbl.findByTripCabId(srchid);
//		if (book.isPresent()) {
//			return book.get();
//		} else {
//			return null;
//		}
//
//	}

	
//	@PutMapping("/update/{employeeId}")
//	public ResponseEntity<BookingRequest> updatebyid(@PathVariable("employeeId") Long id,
//			@RequestBody BookingRequest entryset) {
//		Optional<BookingRequest> entity = repo.findById(id);
//		System.out.print(entity.get());
//		BookingRequest bookingrequest = null;
//		if (entity.isPresent()) {
//			bookingrequest = entity.get();
//			bookingrequest.setStatus(entryset.getStatus());
//
//			this.repo.save(bookingrequest);
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(bookingrequest);
//	}

	//UPDATING NOSHOW STATUS
	@PutMapping("/update/for/{TripId}")
	public ResponseEntity<BookingRequest> updatebytripid(@PathVariable("TripId") Long id,
			@RequestBody List<BookingRequest> entryset) {
		System.out.println(entryset);

		BookingRequest entryset1 = tripdetailsbl.updatebytripid(id, entryset);

		return ResponseEntity.status(HttpStatus.OK).body(entryset1);
	}

	//UPDATING SHOW STATUS
	@PutMapping("/update/for/show/{TripId}")
	public ResponseEntity<BookingRequest> updatebytripidforshow(@PathVariable("TripId") Long id,
			@RequestBody List<BookingRequest> entryset) {
		System.out.print(entryset);

		BookingRequest entryset1 = tripdetailsbl.updatebytripidforshow(id, entryset);

		return ResponseEntity.status(HttpStatus.OK).body(entryset1);
	}

	//UPDATING STATUS AS ONGOING ON START OF TRIP FOR SHOW EMPLOYEE
	@PutMapping("/updateme/Ongoing/{tripCabId}")
	public ResponseEntity<TripCabInfo> updatebytripCabIDforOngoing(@PathVariable("tripCabId") long tripCabID) {
		System.out.print(tripCabID);

		TripCabInfo savedStatus = this.tripdetailsbl.updateTripforOngoing(tripCabID);

		return ResponseEntity.status(HttpStatus.OK).body(savedStatus);
	}

//	@GetMapping(path = "/tripdetails/{id}")
//	public ResponseEntity<List<TripCabInfo>> getTripDetails(@PathVariable("id") long tripCabID) {
//		List<TripCabInfo> tripdetails = this.tripdetailsbl.getTripDetails(tripCabID);
//
//		return ResponseEntity.status(HttpStatus.OK).body(tripdetails);
//	}

	//NOTIFICATION BASED ON TRIP ID AND CAB NUMBER
	@PutMapping(path = "/notification/{cabNumber}/{tripCabID}")
	public TripCabInfo getNotificationByNumber(@PathVariable("cabNumber") String cabNumber,
			@PathVariable("tripCabID") long tripCabID) {
		TripCabInfo tripObj = this.tripdetailsbl.getTripAssignedDetailsByCabNumberaftercancelling(cabNumber, tripCabID);
		return tripObj;
	}

	//GETTING SERVER TIME FOR START TRIP
	@GetMapping(path = "/serverTime")
	public LocalTime getServerTime() {

		return LocalTime.now();
	}

}
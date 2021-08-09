package com.example.demo.bl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.entity.BookingRequest;
import com.example.demo.entity.TripCabInfo;
import com.example.demo.service.TripDetailsService;

@Component
public class TripDetailsBL {

	@Autowired
	TripDetailsService service;
	
	//GETTING TRIP DETAILS BASED ON TRIP ID
	public TripCabInfo findTripCabInfo(long srchid) {

		return this.service.findTripCabInfo(srchid);
	}

	//GETTING TRIP DETAILS BASED ON TRIP ID
	public Optional<List<BookingRequest>> findByTripCabId(long srchid) {

		return this.service.findByTripCabId(srchid);
	}

	//UPDATING NOSHOW STATUS
	public BookingRequest updatebytripid(Long id, List<BookingRequest> entryset) {

		return this.service.updatebytripid(id, entryset);
	}

	//UPDATING SHOW STATUS
	public BookingRequest updatebytripidforshow(Long id, List<BookingRequest> entryset) {
		return this.service.updatebytripidforshow(id, entryset);
	}

	//UPDATING STATUS AS ONGOING ON START OF TRIP FOR SHOW EMPLOYEE
	public TripCabInfo updateTripforOngoing(long tripCabID) {

		return this.service.updateTripforOngoing(tripCabID);
	}

//	public List<TripCabInfo> getTripDetails(long tripCabID) {
//		return this.service.getTripDetails(tripCabID);
//	}

	//NOTIFICATION BASED ON TRIP ID AND CAB NUMBER
	public TripCabInfo getTripAssignedDetailsByCabNumberaftercancelling(String cabNumber, long tripCabID) {

		return this.service.getTripAssignedDetailsByCabNumberaftercancelling(cabNumber, tripCabID);
	}

	

}

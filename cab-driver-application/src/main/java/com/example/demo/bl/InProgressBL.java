package com.example.demo.bl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.entity.BookingRequest;
import com.example.demo.entity.TripCabInfo;
import com.example.demo.service.TripInProgressService;

@Component
public class InProgressBL {

	@Autowired
	private TripInProgressService serviceforinprogress;

	//GETTING EMPLOYEE DETAILS BASED ON SHOW STATUS
	public Optional<List<BookingRequest>> findShowusers(long srchid) {
		return this.serviceforinprogress.findShowUsers(srchid);

	}

	// FOR UPDATING STATUS OF EMPLOYEE
	public BookingRequest storeEmployeeStatus(String employeeID) {

		return this.serviceforinprogress.storeEmployeeStatus(employeeID);
	}

	// FOR UPDATING END STATUS OF CAB
	public TripCabInfo updateTrip(long tripCabID) {

		return this.serviceforinprogress.updateTrip(tripCabID);
	}

	// FOR GETTING SERVER TIME FOR START TIME
	public TripCabInfo getBookingTime(long tripCabID) {

		return this.serviceforinprogress.getBookingTime(tripCabID);
	}

}

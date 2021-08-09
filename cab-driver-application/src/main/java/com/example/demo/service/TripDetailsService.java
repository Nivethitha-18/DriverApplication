package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.BookingRequest;
import com.example.demo.entity.EmployeeDetails;
import com.example.demo.entity.TripCabInfo;
import com.example.demo.repo.BookingRequestRepository;
import com.example.demo.repo.EmployeeDetailsRepository;
import com.example.demo.repo.TripCabInfoRepository;


@Service(value="Service")
public class TripDetailsService {
	
	@Autowired
    private BookingRequestRepository repo;

	@Autowired
	private TripCabInfoRepository triprepo;
	@Autowired
	EmployeeDetailsRepository emprepo;

	//GETTING TRIP DETAILS BASED ON TRIP ID
public Optional<List<BookingRequest>> findByTripCabId(long srchid){
	return this.repo.findByTripCabId(srchid);
	
}


//public List<TripCabInfo> getTripDetails(long tripCabId) {
//
//	return this.triprepo.findTripDetailsByTripCabId(tripCabId);
//}


//UPDATING NOSHOW STATUS
public BookingRequest updatebytripid(long Id,List<BookingRequest> entryset)
{
	
	for(BookingRequest each:entryset) {
		 
		 BookingRequest bookingrequest= repo.findByEmployeeIdAndTripCabId(each.getEmployeeId(),Id).get(); 
		 
		 if(!(bookingrequest.getStatus().equals("Cancelled"))) {
   		 bookingrequest.setStatus("Noshow");
 		 EmployeeDetails employee=emprepo.findById( bookingrequest.getEmployeeId()).get();
 		employee.setEmployeeMail(employee.getEmployeeMail());
   		 employee.setIsBlocked(1);
   		 employee.setBlockedDate(LocalDateTime.now());
   		 emprepo.save(employee);
   		 repo.save(bookingrequest);
		 }
		
		 
	 }

	return null;
	

}

//UPDATING SHOW STATUS
public BookingRequest updatebytripidforshow(long Id,List<BookingRequest> entryset)
{
	
	for(BookingRequest each:entryset) {
		
		 BookingRequest bookingrequest= repo.findByEmployeeIdAndTripCabId(each.getEmployeeId(),Id).get(); 
		 
		 if(!(bookingrequest.getStatus().equals("Cancelled"))) {
   		 bookingrequest.setStatus("Ongoing");
   		 
		 }
		  repo.save(bookingrequest);
		 
	 }
	
	
	
	
	return null;
	

}

//UPDATING STATUS AS ONGOING ON START OF TRIP FOR SHOW EMPLOYEE
public TripCabInfo updateTripforOngoing(long tripCabID) {

	Optional<TripCabInfo> save= triprepo.findById(tripCabID);
	TripCabInfo status= save.get();
	status.setStartTime(LocalTime.now());
	status.setStatus("Onprogress");

	
	return triprepo.save(status);
	
}


//NOTIFICATION BASED ON TRIP ID AND CAB NUMBER
public TripCabInfo getTripAssignedDetailsByCabNumberaftercancelling(String cabNumber,long tripCabId) {

    Optional<TripCabInfo> save= triprepo.findByCabNumberAndTripCabId(cabNumber,tripCabId);
    TripCabInfo status= save.get();
    status.setStartTime(LocalTime.now());
    status.setStatus("Cancelled");
    
    return triprepo.save(status);
}

public TripCabInfo findTripCabInfo(long srchid) {

	Optional<TripCabInfo> trip = this.triprepo.findById(srchid);
	return trip.get();
}


}
package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CabDriverApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabDriverApplication.class, args);
	}
	
//	 @Autowired
//	 private BookingRequestRepository repo; 
//	 
//	 @Autowired
//	 private TripCabInfoRepository triprepo;
//	 
//	 @Autowired
//	 private EmployeeDetailsRepository EmployeeReposs;
//	 
//	 @Autowired
//	 private CabInfoRepository repos;
//	 
//	 @Autowired
//	 private  DriverInfoRepository driverRepo;
	
	
	@Bean
	public CommandLineRunner runner() {
		return new CommandLineRunner() {
			
			
			@Override
			public void run(String... args) throws Exception {
				
				
//				CabInfo cabInfo=new CabInfo("TN 50 S 6677", 101, "Swift", 10, "LC12D90",null , "Jawahar", null, null, null, null, 0);
//				
//				repos.save(cabInfo);			
//				
//				DriverInfo driverInfo = new DriverInfo(101, "Jawahar", "1234567890", 0, null, null, null, null, null, null, 0);
//				
//				driverRepo.save(driverInfo);
//				
//				
//				EmployeeDetails contacts1 = new EmployeeDetails("1001","Ragahavan",null,9123456780l,1,0,null,null,null,null,null,null,null,null,0); 	
//				EmployeeDetails contacts2 = new EmployeeDetails("1002", "santhosh",null,9123849200l,1,0,null,null,null,null,null,null,null,null,0);
//				
//				EmployeeReposs.save(contacts1); 
//				EmployeeReposs.save(contacts2);
//
//				
//				
//				TripCabInfo tripdetails = new TripCabInfo(10014,"TN 50 S 6677","Swift",104,"Bayline","Tambaram",LocalDate.now() ,
//						                                   LocalTime.now(),10,2,8,"Assigned",null,null,null,null,null,null,0);
//				
//				triprepo.save(tripdetails);
//			
//				
//				
//				BookingRequest book2=new BookingRequest(106,"10723","vicky","alphacity","shollinganallur","velachery",
//						                                LocalTime.now(),LocalTime.now(),0,10014,null,null,null,null,"Assigned",null,null,null,null,0);
//				
//				repo.save(book2);
		
			}
		};
	}
	
	
	}

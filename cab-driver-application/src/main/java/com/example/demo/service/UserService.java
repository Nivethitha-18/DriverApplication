package com.example.demo.service;

import java.util.ArrayList;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CabInfo;
import com.example.demo.entity.DriverInfo;
import com.example.demo.repo.CabInfoRepository;
import com.example.demo.repo.DriverInfoRepository;


@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private DriverInfoRepository driverInfoRepo;
	
	@Autowired
	private CabInfoRepository  cabInfoRepo;

	//TO SAVE USER CREDENTIAL IN REPO
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	CabInfo cabInfo=this.cabInfoRepo.findByCabNumber(username);
    	if(cabInfo ==  null) {
			throw new UsernameNotFoundException("Bad Credentials");
		}
    	
    	long driverId=cabInfo.getDriverId();
    	
    	DriverInfo driverInfo=this.driverInfoRepo.findByDriverId(driverId);
        return new User(cabInfo.getCabNumber(),driverInfo.getPassword(),new ArrayList<>());
    }
}
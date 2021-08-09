package com.example.demo.bl;

import java.net.MalformedURLException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.dl.LoginDl;
import com.example.demo.entity.CabInfo;
import com.example.demo.entity.UserRequest;
import com.example.demo.repo.DriverInfoRepository;
import com.example.demo.util.JwtUtils;

@Component
public class LoginBL {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private LoginDl loginDl;

	@Autowired
	DriverInfoRepository dRepo;

	@Autowired
	private JwtUtils jwtUtil;

	//AUTHENTICATE CREDENTIALS
	public String validateUser(@RequestBody UserRequest driver)
			throws ExecutionException, InterruptedException, MalformedURLException {

		String jwt = null;

		CabInfo cabInfo = this.loginDl.findByCabNumber(driver.getLoginId());

		if (cabInfo == null) {
			throw new BadCredentialsException("User Not Found");
		}

		try {
			// spring security authentication
			Authentication auth = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(driver.getLoginId(), driver.getPassword()));
			jwt = "Bearer " + jwtUtil.generateToken(driver.getLoginId());
			SecurityContextHolder.getContext().setAuthentication(auth);

			// this exception occurs on invalid credentials or if account is locked
		} catch (BadCredentialsException | UsernameNotFoundException e) {
			throw e;

		}

		return jwt;
	}

	
	//GETTING CABINFO BASED ON CAB NUMBER
	public Optional<CabInfo> findById(String cabNumber) {
		return this.loginDl.findById(cabNumber);
	}

}

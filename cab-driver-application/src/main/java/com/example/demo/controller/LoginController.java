package com.example.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bl.LoginBL;
import com.example.demo.entity.CabInfo;
import com.example.demo.entity.UserRequest;
import com.example.demo.repo.CabInfoRepository;

@CrossOrigin(origins = { "*" })
@RestController

public class LoginController {

	@Autowired
	private LoginBL loginBl;

	@Autowired
	private CabInfoRepository cabInfoRepo;

	//AUTHENTICATE CREDENTIALS
	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody UserRequest userRequest,HttpServletRequest httpservletRequest ) throws Exception {
		try {
			String jwt = loginBl.validateUser(userRequest);
			httpservletRequest.getSession().setAttribute("Authorization", jwt);
			
			httpservletRequest.getSession().setAttribute("loginId", userRequest.getLoginId());
			
			return ResponseEntity.ok(jwt);

		} catch (BadCredentialsException | UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credential");

		}

	}

	//GETTING CABINFO BASED ON CAB NUMBER
	@GetMapping("/CabInfo/{cabNumber}")
	public ResponseEntity<?> cabInfo(@PathVariable("cabNumber") String cabNumber) {
		Optional<CabInfo> cab = this.loginBl.findById(cabNumber);
		CabInfo cabInfo = cab.get();
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cabInfo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not Found");
		}

	}
}
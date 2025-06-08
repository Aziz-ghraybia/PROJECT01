package com.example.PROJECT1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PROJECT1.DTO.ConfirmationTokenDTO;
import com.example.PROJECT1.DTO.SignupDTO;
import com.example.PROJECT1.enums.ConfirmationType;
import com.example.PROJECT1.service.ConfirmationToken.ConfirmationTokenService;
//import com.example.PROJECT1.DTO.UserDTO;
import com.example.PROJECT1.service.mail.EmailService;
import com.example.PROJECT1.service.user.UserService;


@RestController
@RequestMapping(path="/sign-up")
public class SignupController {

 @Autowired
 private UserService userService;

 @Autowired
 private EmailService emailService;
 @Autowired
 private ConfirmationTokenService tokenService;


 @PostMapping
 //("/sign-up")
 public ResponseEntity<?> signupUser(@RequestBody SignupDTO signupDTO) {
	 if(!userService.hasUserWithEmail(signupDTO.getEmail())) {
		 //step 1:save the disabled new user
		 userService.createUser(signupDTO);
	     // Step 2: Generate a verification code using the EmailService
	     String verificationCode = emailService.generateVerificationCode();
	     // Step 3: Send verification email
	     emailService.sendVerificationCode(signupDTO.getEmail(), verificationCode,ConfirmationType.Signup);
	     //step 5: create the Confirmation token
	     ConfirmationTokenDTO token =tokenService.createToken(signupDTO.getEmail(), verificationCode, ConfirmationType.Signup);
	     // Step 4: Return a success message
	     return new ResponseEntity<>("Email verified successfully. Your account has been created token= "+verificationCode, HttpStatus.CREATED);
	 }
	 else if(userService.isEnabled(signupDTO.getEmail())) {
		 // Step 1: Generate a verification code using the EmailService
	     String verificationCode = emailService.generateVerificationCode();
	     // Step 2: Send verification email
	     emailService.sendVerificationCode(signupDTO.getEmail(), verificationCode,ConfirmationType.Signup);
	     // step 3: create the Confirmation token
	     ConfirmationTokenDTO token =tokenService.createToken(signupDTO.getEmail(), verificationCode, ConfirmationType.Signup);
	     // Step 4: Return a success message
	     return new ResponseEntity<>("Email exist but not enabled. verification  token= "+verificationCode+"sent", HttpStatus.CREATED);
	 }else
		 return new ResponseEntity<>("Email already exist",HttpStatus.BAD_REQUEST);
	
 }
@GetMapping("/confirm")
 public ResponseEntity<?> confirm(@RequestParam String token) {
	if(tokenService.alreadyEnabled(token)) {
    try {
        tokenService.enableUser(token);
        // Account enabled
        return new ResponseEntity<>("Email verified successfully. Your account has been enabled", HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }}
	else 
		return new ResponseEntity<>("this token is already used. Your account has been enabled", HttpStatus.BAD_REQUEST);
 }
}

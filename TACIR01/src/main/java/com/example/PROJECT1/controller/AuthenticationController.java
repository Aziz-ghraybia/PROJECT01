package com.example.PROJECT1.controller;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.PROJECT1.DTO.AuthenticationRequest;
//import com.example.PROJECT1.DTO.AuthenticationResponse;
import com.example.PROJECT1.entities.Membre;
import com.example.PROJECT1.repository.MembreRepository;
import com.example.PROJECT1.service.user.UserService;
import com.example.PROJECT1.utils.JwtUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {
	@Autowired
	UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private MembreRepository userRepository;
	@Autowired
	private JwtUtil jwtUtil;
	public static final String TOKEN_PREFIX="BEARER ";
	public static final String HEADER_STRING="Authorization";
	
	@PostMapping("/authenticate")
	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response) throws BadCredentialsException,DisabledException,UsernameNotFoundException,IOException,JSONException,ServletException{
		// Attempt authentication using provided credentials
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		}catch(BadCredentialsException e){
			 // Handle incorrect credentials exception
			throw new BadCredentialsException("Incorrect Password or Username");
		}catch(DisabledException disabledException) {
			// Handle disabled user exception
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,"User is not available");
			return;
		}
		// Load user details by username
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		// Find user by email (assuming email is username)
		Membre user=userRepository.findFirstByEmail(authenticationRequest.getUsername());
		 // Generate JWT token
		final String jwt=jwtUtil.generateToken(authenticationRequest.getUsername());
		//return new AuthenticationResponse(jwt);
		// Write response with user ID and role as JSON object
		response.getWriter().write(new JSONObject()
									.put("userId",user.getMembreId())
									.put("role",user.getRole())
									.toString()
									);
		 // Add necessary headers to the response
		response.addHeader("Access-Control-Expose-headers","Authorization");
		response.addHeader("Access-Control-Allow-headers","Authorization,X-PINGGOTHER,Origin.X-Requested-With,Content-Type,Accept,X-CustomHeader");
		response.addHeader(HEADER_STRING, TOKEN_PREFIX+jwt);
	}
}

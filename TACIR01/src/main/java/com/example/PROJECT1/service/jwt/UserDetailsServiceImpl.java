package com.example.PROJECT1.service.jwt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.PROJECT1.entities.Membre;
import com.example.PROJECT1.repository.MembreRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private MembreRepository MembreRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		int taille=username.length();
		Membre user;
		if(taille==8) {
			user=MembreRepo.findFirstByPhone(Double.parseDouble(username));
		}
		else {
			user = MembreRepo.findFirstByEmail(username);
		}
		if (user == null) throw new UsernameNotFoundException("Username not found :"+username,null);
		// Create and return a UserDetails object based on the user information  
		return new org.springframework.security.core.userdetails.User(
	              user.getEmail(),            // Username (email)
	              user.getPassword(),         // Password
	              new ArrayList<>()          // Empty list of authorities (roles)
	          );
	}

}

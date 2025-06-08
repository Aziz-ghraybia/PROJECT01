package com.example.PROJECT1.service.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.PROJECT1.DTO.SignupDTO;
import com.example.PROJECT1.DTO.UserDTO;
import com.example.PROJECT1.entities.Membre;
import com.example.PROJECT1.enums.Role;
import com.example.PROJECT1.repository.MembreRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private MembreRepository userRepository;
	
	@PostConstruct
	public void createAdmin() {
		Membre adminAccount=userRepository.findByRole(Role.ADMIN);
		if(adminAccount==null) {
			Membre admin1=new Membre();
			admin1.setRole(Role.ADMIN);
			admin1.setEmail("admin@admin.test");
			admin1.setPassword(new BCryptPasswordEncoder().encode("admin"));
			admin1.setName("admin");
			admin1.setPrename("default");
			userRepository.save(admin1);
		}
	}
	
	@Override
	public UserDTO createUser(SignupDTO signupDTO) {
		// Create a new user entity and populate its attributes
		Membre user=new Membre();
		user.setName(signupDTO.getName());
		user.setPrename(signupDTO.getPrename());
		user.setEmail(signupDTO.getEmail());
		user.setPhone(signupDTO.getPhone());
		user.setRole(Role.USER);
		user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
		 // Save the user to the database
		Membre createdUser=userRepository.save(user);
		// Create a UserDTO object to encapsulate the user data to be returned to the client
		UserDTO userDTO=new UserDTO();
		userDTO.setEmail(createdUser.getEmail());
		userDTO.setMembreId(createdUser.getMembreId());
		userDTO.setName(createdUser.getName());
		userDTO.setPrename(createdUser.getPrename());
		userDTO.setPhone(createdUser.getPhone());
		userDTO.setRole(createdUser.getRole());
		userDTO.setEnabled(createdUser.getEnabled());
		userDTO.setActivated(createdUser.getActivated());
		// Return the UserDTO object containing the user data
		return userDTO;
	}
	@Override
	public boolean hasUserWithEmail(String email) {
		return userRepository.findFirstByEmail(email)!=null;
	}
	@Override
	public void Enabler(String email) {
        Membre user = userRepository.findFirstByEmail(email);
        if (user != null) {
            user.setEnabled(true);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
	}
	@Override
	public boolean isEnabled(String email) {
		Membre user = userRepository.findFirstByEmail(email);
		boolean enabled =user.getEnabled();
		if((user !=null)&&(!enabled))
			return true;
		else
			return false;
		
	}
}

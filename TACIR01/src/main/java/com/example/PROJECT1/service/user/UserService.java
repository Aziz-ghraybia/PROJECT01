package com.example.PROJECT1.service.user;

import com.example.PROJECT1.DTO.SignupDTO;
import com.example.PROJECT1.DTO.UserDTO;

public interface UserService {

	UserDTO createUser(SignupDTO signupDTO);

	boolean hasUserWithEmail(String email);

	void Enabler(String email);

	boolean isEnabled(String email);


}

package com.example.PROJECT1.service.ConfirmationToken;

import com.example.PROJECT1.DTO.ConfirmationTokenDTO;
import com.example.PROJECT1.enums.ConfirmationType;

public interface ConfirmationTokenService {

	ConfirmationTokenDTO createToken(String email, String confirmationCode, ConfirmationType confirmationType);

	void enableUser(String token);

	boolean alreadyEnabled(String token);


}

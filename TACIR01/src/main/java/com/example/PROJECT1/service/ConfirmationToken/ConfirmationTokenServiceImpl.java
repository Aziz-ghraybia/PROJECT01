package com.example.PROJECT1.service.ConfirmationToken;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.PROJECT1.DTO.ConfirmationTokenDTO;
import com.example.PROJECT1.entities.ConfirmationToken;
import com.example.PROJECT1.enums.ConfirmationType;
import com.example.PROJECT1.repository.ConfirmationTokenRepository;
import com.example.PROJECT1.repository.MembreRepository;
import com.example.PROJECT1.service.user.UserService;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
	   private final ConfirmationTokenRepository tokenRepo;
	    private final UserService userService;
	    private final MembreRepository userRepo;

	    // Constructor injection
	    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository tokenRepo, UserService userService, MembreRepository userRepo) {
	        this.tokenRepo = tokenRepo;
	        this.userService = userService;
	        this.userRepo = userRepo;
	    }
	@Override
	public ConfirmationTokenDTO createToken(String email,String confirmationCode,ConfirmationType confirmationType) {
		ConfirmationToken token = new ConfirmationToken();
		token.setConfirmationCode(confirmationCode);
		token.setUserMail(email);
		token.setType(confirmationType);
		token.setCreatedAt(LocalDateTime.now());
		token.setExpiresAt(LocalDateTime.now().plusMinutes(30));
		//Save the token to the database
		ConfirmationToken createToken=tokenRepo.save(token);
		// Create a tokenDTO object to encapsulate the token data to be returned to the client
		ConfirmationTokenDTO tokenDTO=new ConfirmationTokenDTO();
		tokenDTO.setConfirmationCode(token.getConfirmationCode());
		tokenDTO.setUserMail(token.getUserMail());
		tokenDTO.setCreatedAt(token.getCreatedAt());
		tokenDTO.setExpiresAt(token.getExpiresAt());
		tokenDTO.setType(token.getType());
		return tokenDTO;
	}
	@Override
	public void enableUser(String token) {
	       ConfirmationToken confirmToken = tokenRepo.findByConfirmationCode(token);
	       final LocalDateTime expiring=confirmToken.getExpiresAt();
	       final LocalDateTime rn=LocalDateTime.now();
	        if (confirmToken != null && expiring.isAfter(rn)) {
	            userService.Enabler(confirmToken.getUserMail());
	            confirmToken.setConfirmedAt(LocalDateTime.now());
	            tokenRepo.save(confirmToken);
	        } else {
	            throw new IllegalArgumentException("Invalid or expired token");
	        }
		
	}
	@Override
	public boolean alreadyEnabled(String token) {
		ConfirmationToken confirmToken = tokenRepo.findByConfirmationCode(token);
		LocalDateTime confirmDate=confirmToken.getConfirmedAt();
		if(confirmDate!=null)
			return false;
		else
			return true;
	}
}

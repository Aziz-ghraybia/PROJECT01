package com.example.PROJECT1.service.mail;

import com.example.PROJECT1.enums.ConfirmationType;

public interface EmailService {
	String generateVerificationCode();

	boolean verifyCode(String verificationCode, String codeFromClient);

	void sendVerificationCode(String to, String verificationCode, ConfirmationType objective);
}

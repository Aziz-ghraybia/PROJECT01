package com.example.PROJECT1.DTO;

import java.time.LocalDateTime;

import com.example.PROJECT1.enums.ConfirmationType;

public class ConfirmationTokenDTO {
	private String confirmationCode;
	private LocalDateTime createdAt;
	private LocalDateTime expiresAt;
	private LocalDateTime confirmedAt;
	private String userMail;
	private ConfirmationType type;
	public String getConfirmationCode() {
		return confirmationCode;
	}
	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}
	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}
	public void setConfirmedAt(LocalDateTime confirmedAt) {
		this.confirmedAt = confirmedAt;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public ConfirmationType getType() {
		return type;
	}
	public void setType(ConfirmationType type) {
		this.type = type;
	}

}

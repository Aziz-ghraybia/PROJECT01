package com.example.PROJECT1.entities;

import java.time.LocalDateTime;

import com.example.PROJECT1.enums.ConfirmationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ConfirmationToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String confirmationCode;
	private LocalDateTime createdAt;
	private LocalDateTime expiresAt;
	private LocalDateTime confirmedAt;
	private String userMail;
	private ConfirmationType type;
	public ConfirmationType getType() {
		return type;
	}
	public void setType(ConfirmationType type) {
		this.type = type;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
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
	

}

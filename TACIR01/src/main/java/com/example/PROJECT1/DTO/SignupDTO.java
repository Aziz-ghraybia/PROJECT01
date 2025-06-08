package com.example.PROJECT1.DTO;

import com.example.PROJECT1.enums.Role;

import lombok.Data;

@Data
public class SignupDTO {

	private String name;
	private String prename;
	private String Email;
	private Double phone;
	private String password;
	private Role role;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrename() {
		return prename;
	}
	public void setPrename(String prename) {
		this.prename = prename;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Double getPhone() {
		return phone;
	}
	public void setPhone(Double phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

package com.example.PROJECT1.DTO;

import com.example.PROJECT1.enums.Role;

import lombok.Data;

@Data
public class UserDTO {

	private long membreId;
	private String name;
	private String prename;
	private Double phone;
	private String Email;
	private String password;
	private Role role;
	private boolean enabled=false;
	private boolean activated=true;
	private byte[] img;
	/*public UserDTO(long membreId, String name, String prename, String email, Role role) {
		super();
		this.membreId = membreId;
		this.name = name;
		this.prename = prename;
		Email = email;
		this.role = role;
	}*/
	public long getMembreId() {
		return membreId;
	}
	public void setMembreId(long membreId) {
		this.membreId = membreId;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
}

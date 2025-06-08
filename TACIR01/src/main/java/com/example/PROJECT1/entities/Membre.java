package com.example.PROJECT1.entities;



import java.util.List;

import com.example.PROJECT1.DTO.UserDTO;
import com.example.PROJECT1.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name= "Membres")
public class Membre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long membreId;
	private String name;
	private String prename;
	@Email
	private String email;
	private Double phone;
	private String password;
	private Role role;
	
	private boolean enabled=false;
	private boolean activated=true;
	private byte[] img;
    @OneToMany(mappedBy = "membre", cascade = CascadeType.ALL)
    private List<Article> articles;
	/*public UserDTO mapUserToUserDTO() {
		return new UserDTO(membreId,email,name,prename,role);
	}*/
	public long getMembreId() {
		return membreId;
	}
	public void setMembreId(long membreId) {
		this.membreId = membreId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean getEnabled() {
			return enabled;
		}
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
		public boolean getActivated() {
			return activated;
		}
		public void setActivated(boolean activated) {
			this.activated = activated;
		}
	}

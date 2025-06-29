package com.example.PROJECT1.DTO;


import jakarta.persistence.Lob;

import lombok.Data;



@Data
public class CategoryDTO {
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private Long id;
    private String name;

    @Lob
    private String description;

}

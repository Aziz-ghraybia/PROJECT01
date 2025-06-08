package com.example.PROJECT1.entities;


import com.example.PROJECT1.DTO.CategoryDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
	@Table(name = "categories")
	public class Category {

	    @Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	    private String name;
	    @Column(nullable = false)
		@Lob
	    private String description;
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
		public CategoryDTO getCategoryDTO(){
			CategoryDTO categoryDTO=new CategoryDTO();
			categoryDTO.setId(id);
			categoryDTO.setName(name);
			categoryDTO.setDescription(description);
			return categoryDTO;
		}


}

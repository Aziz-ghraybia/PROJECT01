package com.example.PROJECT1.entities;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
	@Table(name = "article_pictures")
	public class ArticlePicture {
	    @Id
	    private String id; // Using a random string as the ID

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "article_id", nullable = false)
	    private Article article;
	    // This field stores the picture data as a byte array
	    @Lob
	    private byte[] pictureData;


	    public ArticlePicture() {
	        boolean uniqueIdFound = false;
	        while (!uniqueIdFound) {
	            try {
	                this.id = generateRandomId();
	                uniqueIdFound = true;
	            } catch (Exception e) {
	                // Collision occurred, generate a new ID
	            }
	        }
	    }


	    private String generateRandomId() {
	        // Logic to generate a random ID, e.g., using UUID
	        return UUID.randomUUID().toString().substring(0,8);
	    }


		public Article getArticle() {
			return article;
		}


		public void setArticle(Article article) {
			this.article = article;
		}


		public byte[] getPictureData() {
			return pictureData;
		}


		public void setPictureData(byte[] pictureData) {
			this.pictureData = pictureData;
		}


		public String getId() {
			return id;
		}

}

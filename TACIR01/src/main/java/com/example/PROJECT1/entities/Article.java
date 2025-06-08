package com.example.PROJECT1.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "articles")
public class Article {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membre_id", nullable = false)
    private Membre membre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    private Date createdAt;
    private Date updatedAt;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticlePicture> pictures = new ArrayList<>();
    
    public void addPicture(ArticlePicture picture) {
        if (pictures.size() < 5) {
            pictures.add(picture);
        } else {
            throw new IllegalStateException("Maximum number of pictures reached.");
        }
    }

		public void setPictures(List<ArticlePicture> pictures) {
		this.pictures = pictures;
	}

		public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}}
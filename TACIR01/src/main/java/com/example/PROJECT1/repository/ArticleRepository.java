package com.example.PROJECT1.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.PROJECT1.entities.Article;
import com.example.PROJECT1.entities.Category;
import com.example.PROJECT1.entities.Membre;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitleContaining(String keyword);
    List<Article> findByMembre(Membre membre);
    List<Article> findByCategory(Category category);
    Long countByCategory(Category category);
}

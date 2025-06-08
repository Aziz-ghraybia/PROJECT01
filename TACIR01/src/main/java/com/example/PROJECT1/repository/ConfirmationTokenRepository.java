package com.example.PROJECT1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.PROJECT1.entities.ConfirmationToken;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long>{
	ConfirmationToken findByConfirmationCode(String confirmationCode);
}

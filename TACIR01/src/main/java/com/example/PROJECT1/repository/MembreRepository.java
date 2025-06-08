package com.example.PROJECT1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PROJECT1.entities.Membre;
import com.example.PROJECT1.enums.Role;
@Repository
public interface MembreRepository extends JpaRepository<Membre,Long> {
Membre findFirstByEmail(String email);
Membre findFirstByPhone(Double phone);
Membre findByRole(Role admin);
}

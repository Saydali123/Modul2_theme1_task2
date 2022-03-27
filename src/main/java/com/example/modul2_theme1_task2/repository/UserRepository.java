package com.example.modul2_theme1_task2.repository;

import com.example.modul2_theme1_task2.domains.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AuthUser, Long> {

    Boolean existsByEmail(String email);
}

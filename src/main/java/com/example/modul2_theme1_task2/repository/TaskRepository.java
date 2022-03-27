package com.example.modul2_theme1_task2.repository;

import com.example.modul2_theme1_task2.domains.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}

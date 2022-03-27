package com.example.modul2_theme1_task2.repositories;

import com.example.modul2_theme1_task2.domains.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

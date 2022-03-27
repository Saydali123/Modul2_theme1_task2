package com.example.modul2_theme1_task2.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String text;

    private String solution;

    private String hint;

    private String method;

    private boolean has_star;

    @ManyToOne
    private Language language;

}

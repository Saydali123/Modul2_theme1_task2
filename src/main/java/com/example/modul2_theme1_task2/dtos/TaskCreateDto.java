package com.example.modul2_theme1_task2.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateDto {
    public String name;

    public String text;

    public String solution;

    public String hint;

    public String method;

    public boolean has_star;

    public Long languageId;

}

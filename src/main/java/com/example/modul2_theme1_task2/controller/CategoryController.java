package com.example.modul2_theme1_task2.controller;

import com.example.modul2_theme1_task2.domains.AuthUser;
import com.example.modul2_theme1_task2.domains.Category;
import com.example.modul2_theme1_task2.response.ApiResponse;
import com.example.modul2_theme1_task2.service.CategoryService;
import com.example.modul2_theme1_task2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Category category){
        ApiResponse apiResponse = categoryService.add(category);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatusCode());
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Category category, @RequestParam("id") Long id){
        ApiResponse apiResponse = categoryService.edit(category,id);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatusCode());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        ApiResponse apiResponse = categoryService.deleteById(id);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatusCode());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getList(){
        ApiResponse apiResponse = categoryService.getList();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatusCode());
    }

}

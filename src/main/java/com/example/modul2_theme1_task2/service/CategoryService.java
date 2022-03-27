package com.example.modul2_theme1_task2.service;

import com.example.modul2_theme1_task2.domains.Category;
import com.example.modul2_theme1_task2.repositories.CategoryRepository;
import com.example.modul2_theme1_task2.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private  CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse add(Category category){
        if(categoryRepository.existsByName(category.getName()))
            return new ApiResponse("This category is already exist!", false, HttpStatus.EXPECTATION_FAILED);
        else{
            categoryRepository.save(category);
            return new ApiResponse("Successfully added!", true, HttpStatus.OK);
        }
    }

    public ApiResponse edit(Category category, Long id){
        category.setId(id);
        categoryRepository.save(category);
        return new ApiResponse("Successfully updated!", true, HttpStatus.OK);
    }

    public ApiResponse deleteById(Long id){
        boolean existsById = categoryRepository.existsById(id);
        if(existsById){
            categoryRepository.deleteById(id);
            return new ApiResponse("Successfully deleted!", true,HttpStatus.OK);
        }else
            return new ApiResponse("Category is not found!", false, HttpStatus.NOT_FOUND);
    }


    public ApiResponse getList(){
        List<Category> categories = categoryRepository.findAll();

        if(categories.size() == 0 ){
            return new ApiResponse("Category list is empty!", false);
        }else
            return new ApiResponse("Category List", true, categories, HttpStatus.OK);

    }

}

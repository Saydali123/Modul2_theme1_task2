package com.example.modul2_theme1_task2.controller;

import com.example.modul2_theme1_task2.domains.Category;
import com.example.modul2_theme1_task2.dto.AnswerDTO;
import com.example.modul2_theme1_task2.response.ApiResponse;
import com.example.modul2_theme1_task2.service.AnswerService;
import com.example.modul2_theme1_task2.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answer")
public class AnswerController {


    private final AnswerService answerservice;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AnswerDTO answerDTO){
        ApiResponse apiResponse = answerservice.add(answerDTO);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatusCode());
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody AnswerDTO answerDTO, @RequestParam("id") Long id){
        ApiResponse apiResponse = answerservice.edit(answerDTO,id);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatusCode());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getList(){
        ApiResponse apiResponse = answerservice.getList();
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatusCode());
    }

}

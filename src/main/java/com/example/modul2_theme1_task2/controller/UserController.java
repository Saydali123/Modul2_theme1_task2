package com.example.modul2_theme1_task2.controller;

import com.example.modul2_theme1_task2.domains.AuthUser;
import com.example.modul2_theme1_task2.response.ApiResponse;
import com.example.modul2_theme1_task2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody AuthUser user){
        ApiResponse apiResponse = userService.add(user);
        return new ResponseEntity<ApiResponse>(apiResponse, apiResponse.getHttpStatusCode());
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody AuthUser user, @RequestParam("id") Long id){
        ApiResponse apiResponse = userService.edit(user,id);
        return new ResponseEntity<ApiResponse>(apiResponse, apiResponse.getHttpStatusCode());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam("id") Long id){
        ApiResponse apiResponse = userService.deleteById(id);
        return new ResponseEntity<ApiResponse>(apiResponse, apiResponse.getHttpStatusCode());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getList(){
        ApiResponse apiResponse = userService.getList();
        return new ResponseEntity<ApiResponse>(apiResponse, apiResponse.getHttpStatusCode());
    }

}

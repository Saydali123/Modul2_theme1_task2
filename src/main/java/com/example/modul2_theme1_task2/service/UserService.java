package com.example.modul2_theme1_task2.service;

import com.example.modul2_theme1_task2.domains.AuthUser;
import com.example.modul2_theme1_task2.repository.UserRepository;
import com.example.modul2_theme1_task2.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse add(AuthUser authUser){
        if(userRepository.existsByEmail(authUser.getEmail()))
            return new ApiResponse("This email is already exist!",false, HttpStatus.EXPECTATION_FAILED);
        else{
            userRepository.save(authUser);
            return new ApiResponse("Successfully registered!", true, HttpStatus.OK);
        }
    }

    public ApiResponse edit(AuthUser authUser, Long id){
        authUser.setId(id);
        userRepository.save(authUser);
        return new ApiResponse("Successfully updated!", true, HttpStatus.OK);
    }

    public ApiResponse deleteById(Long id){
        boolean existsById = userRepository.existsById(id);
        if(existsById){
            userRepository.deleteById(id);
            return new ApiResponse("Successfully deleted!", true,HttpStatus.OK);
        }else
            return new ApiResponse("User is not found!", false, HttpStatus.NOT_FOUND);
    }

    public ApiResponse getList(){
         List<AuthUser> authUserList = userRepository.findAll();

         if(authUserList.size() == 0 ){
             return new ApiResponse("User list is empty!", false);
         }else
             return new ApiResponse("User List", true, authUserList, HttpStatus.OK);

    }
}

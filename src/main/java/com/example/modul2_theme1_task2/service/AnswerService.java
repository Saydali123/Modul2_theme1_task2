package com.example.modul2_theme1_task2.service;

import com.example.modul2_theme1_task2.domains.Answer;
import com.example.modul2_theme1_task2.domains.AuthUser;
import com.example.modul2_theme1_task2.domains.Category;
import com.example.modul2_theme1_task2.domains.Task;
import com.example.modul2_theme1_task2.dto.AnswerDTO;
import com.example.modul2_theme1_task2.repository.AnswerRepository;
import com.example.modul2_theme1_task2.repository.TaskRepository;
import com.example.modul2_theme1_task2.repository.UserRepository;
import com.example.modul2_theme1_task2.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {


    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public AnswerService(AnswerRepository answerRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }


    public ApiResponse add(AnswerDTO answerDTO){
        AuthUser byId = userRepository.getById(answerDTO.getUserId());
        Task task = taskRepository.getById(answerDTO.getTaskId());
        Answer answer = new Answer();
        answer.setTask(task);
        answer.setUser(byId);
        answer.setCorrect(answerDTO.isCorrect());
        answer.setText(answerDTO.getText());
        answerRepository.save(answer);
        return new ApiResponse("Successfully added!", true, HttpStatus.OK);

    }

    public ApiResponse edit(AnswerDTO answerDTO, Long id){
        AuthUser byId = userRepository.getById(answerDTO.getUserId());
        Task task = taskRepository.getById(answerDTO.getTaskId());
        Answer answer = new Answer();

        answer.setTask(task);
        answer.setUser(byId);
        answer.setCorrect(answerDTO.isCorrect());
        answer.setText(answerDTO.getText());
        answer.setId(id);
        answerRepository.save(answer);

        return new ApiResponse("Successfully updated!", true, HttpStatus.OK);
    }



    public ApiResponse getList(){
        List<Answer> answers = answerRepository.findAll();

        if(answers.size() == 0 ){
            return new ApiResponse("Answer list is empty!", false);
        }else
            return new ApiResponse("Answer List", true, answers, HttpStatus.OK);

    }
}

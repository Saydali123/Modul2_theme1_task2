package com.example.modul2_theme1_task2.controller;

import com.example.modul2_theme1_task2.domains.Language;
import com.example.modul2_theme1_task2.domains.Task;
import com.example.modul2_theme1_task2.dtos.TaskCreateDto;
import com.example.modul2_theme1_task2.dtos.TaskUpdateDto;
import com.example.modul2_theme1_task2.repositories.LanguageRepository;
import com.example.modul2_theme1_task2.repositories.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private final TaskRepository repository;
    private final LanguageRepository languageRepository;

    public TaskController(TaskRepository repository, LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
        this.repository = repository;
    }

    @PostMapping("/create")
    public ResponseEntity<Long> create(TaskCreateDto createDto) {
        Task task = new Task();
        task.setName(createDto.name);
        task.setHas_star(createDto.has_star);
        task.setMethod(createDto.method);
        task.setSolution(createDto.solution);
        task.setText(createDto.text);
        task.setHint(createDto.hint);

        Long languageId = createDto.languageId;
        Optional<Language> byId = languageRepository.findById(languageId);
        byId.ifPresent(task::setLanguage);


        return ResponseEntity.ok(repository.save(task).getId());
    }


    @PostMapping("/update")
    public ResponseEntity<Long> update(TaskUpdateDto updateDto) {
        Task task = new Task();

        Long languageId = updateDto.languageId;
        Optional<Language> byId = languageRepository.findById(languageId);

        byId.ifPresent(task::setLanguage);
        task.setName(updateDto.name);
        task.setHas_star(updateDto.has_star);
        task.setMethod(updateDto.method);
        task.setSolution(updateDto.solution);
        task.setText(updateDto.text);
        task.setHint(updateDto.hint);

        return ResponseEntity.ok(repository.save(task).getId());
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);

        return ResponseEntity.accepted().build();
    }


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Task> get(@PathVariable(value = "id") Long id) {

        return ResponseEntity.ok(repository.findById(id).get());
    }


    @GetMapping(value = "/list")
    public ResponseEntity<List<Task>> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> all = repository.findAll(pageable);
        List<Task> collect = all.get().collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }
}

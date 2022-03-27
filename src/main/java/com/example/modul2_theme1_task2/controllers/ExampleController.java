package com.example.modul2_theme1_task2.controllers;

import com.example.modul2_theme1_task2.domains.Example;
import com.example.modul2_theme1_task2.dtos.ExampleCreateDto;
import com.example.modul2_theme1_task2.repositories.ExampleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "example")
public class ExampleController {

    private final ExampleRepository repository;

    public ExampleController(ExampleRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    public ResponseEntity<Long> create(ExampleCreateDto examCreateDto) {
        Example example = new Example();
        example.setText(examCreateDto.text);
        example.setTask_id(examCreateDto.taskId);

        return ResponseEntity.ok(repository.save(example).getId());
    }


    @PostMapping("/update")
    public ResponseEntity<Long> update(Example example) {

        return ResponseEntity.ok(repository.save(example).getId());
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
        return ResponseEntity.accepted().build();
    }


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Example> get(@PathVariable(value = "id") Long id) {

        return ResponseEntity.ok(repository.findById(id).get());
    }


    @GetMapping(value = "/list")
    public ResponseEntity<List<Example>> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Example> all = repository.findAll(pageable);
        List<Example> collect = all.get().collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }
}

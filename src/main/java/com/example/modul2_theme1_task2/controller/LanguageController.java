package com.example.modul2_theme1_task2.controller;

import com.example.modul2_theme1_task2.domains.Language;
import com.example.modul2_theme1_task2.dtos.LanguageCreateDto;
import com.example.modul2_theme1_task2.repositories.LanguageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "example")
public class LanguageController {

    private final LanguageRepository repository;

    public LanguageController(LanguageRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody LanguageCreateDto createDto) {
        Language language = new Language();
        language.setName(createDto.name);

        return ResponseEntity.ok(repository.save(language).getId());
    }


    @PostMapping("/update")
    public ResponseEntity<Long> update(@RequestBody Language language) {

        return ResponseEntity.ok(repository.save(language).getId());
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
        return ResponseEntity.accepted().build();
    }


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Language> get(@PathVariable(value = "id") Long id) {
        Optional<Language> byId = repository.findById(id);
        if (byId.isPresent()) {
            Language language = byId.get();
            return ResponseEntity.ok(language);
        }
        return ResponseEntity.ok(null);
    }


    @GetMapping(value = "/list")
    public ResponseEntity<List<Language>> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Language> all = repository.findAll(pageable);
        List<Language> collect = all.get().collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }
}

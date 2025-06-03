package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Person getPersonById(
            @PathVariable("id") int id
    ) {
        return repository.findById(id).orElse(null);
    }

}
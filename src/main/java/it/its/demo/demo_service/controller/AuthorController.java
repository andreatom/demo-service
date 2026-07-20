package it.its.demo.demo_service.controller;

import it.its.demo.demo_service.dto.AuthorDto;
import it.its.demo.demo_service.dto.InsertAuthorDto;
import it.its.demo.demo_service.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/{id}/v1")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDto findById(
            @PathVariable Integer id
    ){
        return authorService.findById(id);
    }

    @PostMapping("/v1")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto insert(
            @Valid @RequestBody InsertAuthorDto insertAuthorDto
            ){
        return authorService.insert(insertAuthorDto);
    }


}

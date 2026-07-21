package it.its.demo.demo_service.service;

import it.its.demo.demo_service.dto.author.ResAuthorDto;
import it.its.demo.demo_service.dto.author.ResInsertAuthorDto;
import it.its.demo.demo_service.exceptions.AuthorNotFoundException;
import it.its.demo.demo_service.mapper.AuthorMapper;
import it.its.demo.demo_service.model.Author;
import it.its.demo.demo_service.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public ResAuthorDto findById(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new AuthorNotFoundException(id)
        );

        return authorMapper.toDto(author);
    }

    public ResAuthorDto insert(ResInsertAuthorDto resInsertAuthorDto) {

        Author author = authorMapper.toModel(resInsertAuthorDto);

        authorRepository.save(author);

        return authorMapper.toDto(author);
    }

    public void delete(Integer id){
        authorRepository.findById(id).orElseThrow(
                () -> new AuthorNotFoundException(id)
        );
        authorRepository.deleteById(id);
    }

}

package com.library.api.service.impl;

import com.library.api.converter.BookFamilyConverter;
import com.library.api.exception.EntityNotFoundException;
import com.library.api.model.BookFamily;
import com.library.api.model.dto.BookFamilyDto;
import com.library.api.repository.BookFamilyRepository;
import com.library.api.service.BookFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookFamilyServiceImpl implements BookFamilyService {

    @Autowired
    private BookFamilyRepository bookFamilyRepository;

    @Autowired
    private BookFamilyConverter bookFamilyConverter;

    @Override
    public List<BookFamilyDto> getAllBookFamily() {
        return bookFamilyConverter.entityToDto(bookFamilyRepository.findAll());
    }

    @Override
    public ResponseEntity<BookFamilyDto> getBookFamilyById(Long id) {
        Optional<BookFamily> bookFamilyData = bookFamilyRepository.findById(id);
        if (bookFamilyData.isPresent()) {
            return new ResponseEntity<>(bookFamilyConverter.entityToDto(bookFamilyData.get()), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException(BookFamily.class, "id", id.toString());
        }
    }

    @Override
    public ResponseEntity<BookFamilyDto> createBookFamily(BookFamilyDto bookFamilyDto) {
        try {
            BookFamily _bookFamily = bookFamilyRepository
                    .save(bookFamilyConverter.dtoToEntity(bookFamilyDto));
            return new ResponseEntity<>(bookFamilyConverter.entityToDto(_bookFamily), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<BookFamilyDto> updateBookFamily(Long id, BookFamilyDto bookFamilyDto) {
        Optional<BookFamily> bookFamilyData = bookFamilyRepository.findById(id);
        if (bookFamilyData.isPresent()) {
            BookFamily _bookFamily = bookFamilyData.get();
            _bookFamily.setName(bookFamilyDto.getName());
            return new ResponseEntity<>(bookFamilyConverter.entityToDto(bookFamilyRepository.save(_bookFamily)), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException(BookFamily.class, "id", id.toString());
        }
    }
}

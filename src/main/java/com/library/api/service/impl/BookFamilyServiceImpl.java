package com.library.api.service.impl;

import com.library.api.exception.EntityNotFoundException;
import com.library.api.model.BookFamily;
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

    @Override
    public List<BookFamily> getAllBookFamily() {
        return bookFamilyRepository.findAll();
    }

    @Override
    public ResponseEntity<BookFamily> getBookFamilyById(Long id) {
        Optional<BookFamily> bookFamilyData = bookFamilyRepository.findById(id);
        if (bookFamilyData.isPresent()) {
            return new ResponseEntity<>(bookFamilyData.get(), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException(BookFamily.class, "id", id.toString());
        }
    }

    @Override
    public ResponseEntity<BookFamily> createBookFamily(BookFamily bookFamily) {
        try {
            BookFamily _bookFamily = bookFamilyRepository
                    .save(bookFamily);
            return new ResponseEntity<>(_bookFamily, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<BookFamily> updateBookFamily(Long id, BookFamily bookFamily) {
        Optional<BookFamily> bookFamilyData = bookFamilyRepository.findById(id);
        if (bookFamilyData.isPresent()) {
            BookFamily _bookFamily = bookFamilyData.get();
            _bookFamily.setName(bookFamily.getName());
            return new ResponseEntity<>(bookFamilyRepository.save(_bookFamily), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException(BookFamily.class, "id", id.toString());
        }
    }
}

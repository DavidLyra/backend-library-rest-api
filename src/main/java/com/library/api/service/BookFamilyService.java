package com.library.api.service;

import com.library.api.model.BookFamily;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookFamilyService {

    List<BookFamily> getAllBookFamily();
    ResponseEntity<BookFamily> getBookFamilyById(Long id);
    ResponseEntity<BookFamily> createBookFamily(BookFamily bookFamily);
    ResponseEntity<BookFamily> updateBookFamily(Long id, BookFamily bookFamily);
}

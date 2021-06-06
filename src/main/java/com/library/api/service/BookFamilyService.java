package com.library.api.service;

import com.library.api.model.dto.BookFamilyDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookFamilyService {

    List<BookFamilyDto> getAllBookFamily();
    ResponseEntity<BookFamilyDto> getBookFamilyById(Long id);
    ResponseEntity<BookFamilyDto> createBookFamily(BookFamilyDto bookFamilyDto);
    ResponseEntity<BookFamilyDto> updateBookFamily(Long id, BookFamilyDto bookFamilyDto);
}

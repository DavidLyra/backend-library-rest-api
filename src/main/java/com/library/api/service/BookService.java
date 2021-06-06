package com.library.api.service;

import com.library.api.model.dto.BookDto;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBook();
    ResponseEntity<BookDto> getBookFamilyById(Long id);
    ResponseEntity<BookDto> createBook(BookDto bookDto);
    ResponseEntity<BookDto> updateBook(Long id, BookDto bookDto);
    ResponseEntity<Resource> exportAndSendBookFtp(Long id);
    ResponseEntity<Resource> exportAndSendBookFamilyListFtp(Long id);
}

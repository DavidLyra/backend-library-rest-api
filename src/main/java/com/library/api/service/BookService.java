package com.library.api.service;

import com.library.api.model.Book;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    List<Book> getAllBook();
    ResponseEntity<Book> getBookFamilyById(Long id);
    ResponseEntity<Book> createBook(Book book);
    ResponseEntity<Book> updateBook(Long id, Book book);
    ResponseEntity<Resource> exportAndSendBookFtp(Long id);
    ResponseEntity<Resource> exportAndSendBookFamilyListFtp(Long id);
}

package com.library.api.service.impl;

import com.library.api.model.Book;
import com.library.api.repository.BookRepository;
import com.library.api.service.BookService;
import com.library.api.service.SendFileFTPService;
import com.library.api.util.BookCsvExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SendFileFTPService sendFileFTPService;

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public ResponseEntity<Book> getBookFamilyById(Long id) {
        Optional<Book> bookData = bookRepository.findById(id);
        return bookData.map(book -> new ResponseEntity<>(book, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Book> createBook(Book book) {
        try {
            Book _book = bookRepository.save(book);
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Book> updateBook(Long id, Book book) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setTitle(book.getTitle());
            _book.setBookFamily(book.getBookFamily());
            return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Resource> exportAndSendBookFtp(Long id) {
        try {
            Optional<Book> book = bookRepository.findById(id);
            if (book.isPresent()) {
                sendFileFTPService.sendFileFtp(BookCsvExporter.booksToCSV(Collections.singletonList(book.get())), "books.csv", "");
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Resource> exportAndSendBookFamilyListFtp(Long id) {
        try {
            Optional<List<Book>> bookList = bookRepository.findByBookFamilyId(id);
            if (bookList.isPresent()) {
                sendFileFTPService.sendFileFtp(BookCsvExporter.booksToCSV(bookList.get()), "books.csv", "");
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

package com.library.api.controller;

import com.library.api.model.Book;
import com.library.api.repository.BookRepository;
import com.library.api.service.SendFileFTPService;
import com.library.api.util.BookCsvExporter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/book")
@Api(tags = "Book Controller", value = "Library Management REST API - Lampiris")
public class BookController implements Serializable {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SendFileFTPService sendFileFTPService;

    @GetMapping
    @ApiOperation(value = "List all the book", response = Book.class)
    @PermitAll
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "List all the book by id", response = Book.class)
    @PermitAll
    public ResponseEntity<Book> getBookFamilyById(@PathVariable("id") Long id) {

        Optional<Book> bookData = bookRepository.findById(id);
        return bookData.map(book -> new ResponseEntity<>(book, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "Add a book")
    @PermitAll
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        try {
            Book _book = bookRepository
                    .save(book);
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a book")
    @PermitAll
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @Valid @RequestBody Book book) {
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

    @PostMapping("/{id}/export/send")
    @ApiOperation(value = "Export a book and send to FTP")
    @PermitAll
    public ResponseEntity<Resource> exportAndSendBookFtp(@PathVariable("id") Long id) {
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

    @PostMapping("/family/{familyId}/export/send")
    @ApiOperation(value = "Export a book family and send to FTP")
    @PermitAll
    public ResponseEntity<Resource> exportAndSendBookFamilyListFtp(@PathVariable("familyId") Long id) {
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

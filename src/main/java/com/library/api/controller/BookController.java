package com.library.api.controller;

import com.library.api.model.Book;
import com.library.api.model.dto.BookDto;
import com.library.api.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@Api(tags = "Book Controller", value = "Library Management REST API - Lampiris")
public class BookController implements Serializable {

    @Autowired
    private BookService bookService;

    @GetMapping
    @ApiOperation(value = "List all the book", response = Book.class)
    @PermitAll
    public List<BookDto> getAllBook() {
        return bookService.getAllBook();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "List all the book by id", response = Book.class)
    @PermitAll
    public ResponseEntity<BookDto> getBookFamilyById(@PathVariable("id") Long id) {
        return bookService.getBookFamilyById(id);
    }

    @PostMapping
    @ApiOperation(value = "Add a book")
    @PermitAll
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a book")
    @PermitAll
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookDto bookDto) {
        return bookService.updateBook(id, bookDto);
    }

    @PostMapping("/{id}/export/send")
    @ApiOperation(value = "Export a book and send to FTP")
    @PermitAll
    public ResponseEntity<Resource> exportAndSendBookFtp(@PathVariable("id") Long id) {
        return bookService.exportAndSendBookFtp(id);
    }

    @PostMapping("/family/{familyId}/export/send")
    @ApiOperation(value = "Export a book family and send to FTP")
    @PermitAll
    public ResponseEntity<Resource> exportAndSendBookFamilyListFtp(@PathVariable("familyId") Long id) {
        return bookService.exportAndSendBookFamilyListFtp(id);
    }
}

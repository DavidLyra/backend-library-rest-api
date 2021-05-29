package com.library.api.controller;

import com.library.api.model.Book;
import com.library.api.repository.BookRepository;
import com.library.api.util.BookExcelExporter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/book")
@Api(tags = "Book Controller", value = "Library Management REST API - Lampiris")
public class BookController implements Serializable {

    @Autowired
    private BookRepository bookRepository;

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

    @PostMapping("/export/send")
    @ApiOperation(value = "Export books and send to FTP")
    @PermitAll
    public ResponseEntity<Book> exportAndSendFtpBookList(@Valid @RequestBody List<Book> books) {
        try {
            BookExcelExporter excelExporter = new BookExcelExporter(books);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
            httpHeaders.set( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=books.xlsx");
            return new ResponseEntity( excelExporter, httpHeaders, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}

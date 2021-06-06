package com.library.api.controller;

import com.library.api.model.BookFamily;
import com.library.api.model.dto.BookFamilyDto;
import com.library.api.service.BookFamilyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bookfamilies")
@Api(tags = "Book Family Controller", value = "Library Management REST API - Lampiris")
public class BookFamilyController implements Serializable {

    @Autowired
    private BookFamilyService bookFamilyService;

    @GetMapping
    @ApiOperation(value = "List all the book families", response = BookFamily.class)
    @PermitAll
    public List<BookFamilyDto> getAllBookFamily() {
        return bookFamilyService.getAllBookFamily();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "List all the book families by id", response = BookFamily.class)
    @PermitAll
    public ResponseEntity<BookFamilyDto> getBookFamilyById(@PathVariable("id") Long id) {
           return bookFamilyService.getBookFamilyById(id);
    }

    @PostMapping
    @ApiOperation(value = "Add a book family")
    @PermitAll
    public ResponseEntity<BookFamilyDto> createBookFamily(@Valid @RequestBody BookFamilyDto bookFamilyDto) {
        return bookFamilyService.createBookFamily(bookFamilyDto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a book family")
    @PermitAll
    public ResponseEntity<BookFamilyDto> updateBookFamily(@PathVariable("id") Long id,
                                                          @Valid @RequestBody BookFamilyDto bookFamilyDto) {
        return bookFamilyService.updateBookFamily(id, bookFamilyDto);
    }
}

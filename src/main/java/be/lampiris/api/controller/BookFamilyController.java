package be.lampiris.api.controller;

import be.lampiris.api.model.BookFamily;
import be.lampiris.api.repository.BookFamilyRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bookfamily")
@Api(tags = "Book Family Controller", value = "Backend Library REST API - Lampiris")
public class BookFamilyController implements Serializable {

    @Autowired
    private BookFamilyRepository bookFamilyRepository;

    @GetMapping
    public List<BookFamily> getAllBookFamily() {
        return bookFamilyRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "View book family data by id", response = BookFamily.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved.<br> Attributes with null values will not be displayed"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource."),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found.")
    })
    public ResponseEntity<BookFamily> getBookFamilyById(@PathVariable("id") Long id) {
        Optional<BookFamily> bookFamilyData = bookFamilyRepository.findById(id);

        if (bookFamilyData.isPresent()) {
            return new ResponseEntity<>(bookFamilyData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ApiOperation(value = "Add a book family")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created."),
            @ApiResponse(code = 400, message = "Bad request."),
            @ApiResponse(code = 401, message = "You are not authorized to create the resource.")
    })
    public ResponseEntity<BookFamily> createBookFamily(@RequestBody BookFamily bookFamily) {
        try {
            BookFamily _bookFamily = bookFamilyRepository
                    .save(bookFamily);
            return new ResponseEntity<>(_bookFamily, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a book family")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated."),
            @ApiResponse(code = 401, message = "You are not authorized to update the resource."),
            @ApiResponse(code = 404, message = "The resource you were trying to update is not found.")
    })
    public ResponseEntity<BookFamily> updateBookFamily(@PathVariable("id") Long id, @RequestBody BookFamily bookFamily) {
        Optional<BookFamily> bookFamilyData = bookFamilyRepository.findById(id);

        if (bookFamilyData.isPresent()) {
            BookFamily _bookFamily = bookFamilyData.get();
            _bookFamily.setName(bookFamily.getName());
            return new ResponseEntity<>(bookFamilyRepository.save(_bookFamily), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

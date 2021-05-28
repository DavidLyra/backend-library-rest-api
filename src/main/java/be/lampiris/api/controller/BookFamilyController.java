package be.lampiris.api.controller;

import be.lampiris.api.model.BookFamily;
import be.lampiris.api.repository.BookFamilyRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
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
    @ApiOperation(value = "List all the book families", response = BookFamily.class)
    @PermitAll
    public List<BookFamily> getAllBookFamily() {
        return bookFamilyRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "List all the book families by id", response = BookFamily.class)
    @PermitAll
    public ResponseEntity<BookFamily> getBookFamilyById(@PathVariable("id") Long id) {

        Optional<BookFamily> bookFamilyData = bookFamilyRepository.findById(id);
        return bookFamilyData.map(bookFamily -> new ResponseEntity<>(bookFamily, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "Add a book family")
    @PermitAll
    public ResponseEntity<BookFamily> createBookFamily(@Valid @RequestBody BookFamily bookFamily) {
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
    @PermitAll
    public ResponseEntity<BookFamily> updateBookFamily(@PathVariable("id") Long id, @Valid @RequestBody BookFamily bookFamily) {
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

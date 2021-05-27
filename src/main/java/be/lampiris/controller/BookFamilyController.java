package be.lampiris.controller;

import be.lampiris.model.BookFamily;
import be.lampiris.repository.BookFamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bookfamily")
public class BookFamilyController implements Serializable {

    @Autowired
    private BookFamilyRepository bookFamilyRepository;

    @GetMapping
    public List<BookFamily> getAllBookFamily() {
        return bookFamilyRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookFamily> getBookFamilyById(@PathVariable("id") Long id) {
        Optional<BookFamily> bookFamilyData = bookFamilyRepository.findById(id);

        if (bookFamilyData.isPresent()) {
            return new ResponseEntity<>(bookFamilyData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
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

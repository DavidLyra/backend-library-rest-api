package be.lampiris.controller;

import be.lampiris.model.BookFamily;
import be.lampiris.repository.BookFamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bookfamily")
public class BookFamilyController implements Serializable {

    @Autowired
    private BookFamilyRepository bookFamilyRepository;

    @GetMapping
    public List<BookFamily> bookFamilyList() {
        return bookFamilyRepository.findAll();
    }

    @GetMapping("/library/{libraryId}")
    public List<BookFamily> bookFamilyListByLibrary(@PathVariable("libraryId") Long libraryId) {
        return bookFamilyRepository.findByLibrary(libraryId);
    }
}

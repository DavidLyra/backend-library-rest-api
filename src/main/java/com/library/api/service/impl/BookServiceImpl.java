package com.library.api.service.impl;

import com.library.api.converter.BookConverter;
import com.library.api.exception.EntityNotFoundException;
import com.library.api.model.Book;
import com.library.api.model.dto.BookDto;
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
    private BookConverter bookConverter;

    @Autowired
    private SendFileFTPService sendFileFTPService;

    @Override
    public List<BookDto> getAllBook() {
        return bookConverter.entityToDto(bookRepository.findAll());
    }

    @Override
    public ResponseEntity<BookDto> getBookFamilyById(Long id) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookConverter.entityToDto(bookData.get()), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException(Book.class, "id", id.toString());
        }
    }

    @Override
    public ResponseEntity<BookDto> createBook(BookDto bookDto) {
        try {
            Book _book = bookRepository.save(bookConverter.dtoToEntity(bookDto));
            return new ResponseEntity<>(bookConverter.entityToDto(_book), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BookDto> updateBook(Long id, BookDto bookDto) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setTitle(bookDto.getTitle());
            _book.setBookFamily(bookDto.getBookFamily());
            return new ResponseEntity<>(bookConverter.entityToDto(bookRepository.save(_book)), HttpStatus.OK);
        } else {
            throw new EntityNotFoundException(Book.class, "id", id.toString());
        }
    }

    @Override
    public ResponseEntity<Resource> exportAndSendBookFtp(Long id) {
        try {
            Optional<Book> book = bookRepository.findById(id);
            if (book.isPresent()) {
                sendFileFTPService.sendFileFtp(BookCsvExporter.booksToCSV(Collections.singletonList(book.get())), "books.csv", "");
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else throw new EntityNotFoundException(Book.class, "id", id.toString());
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
            } else throw new EntityNotFoundException(Book.class, "id", id.toString());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

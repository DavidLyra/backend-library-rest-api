package com.library.api.converter;

import com.library.api.model.Book;
import com.library.api.model.dto.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookConverter {

    public BookDto entityToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setBookFamily(book.getBookFamily());
        return bookDto;
    }

    public List<BookDto> entityToDto(List<Book> book) {
        return book.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Book dtoToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setBookFamily(bookDto.getBookFamily());
        return book;
    }

    public List<Book> dtoToEntity(List<BookDto> bookDtos) {
        return bookDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}

package com.library.api.converter;

import com.library.api.model.BookFamily;
import com.library.api.model.dto.BookFamilyDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookFamilyConverter {

    public BookFamilyDto entityToDto(BookFamily bookFamily) {
        BookFamilyDto bookFamilyDto = new BookFamilyDto();
        bookFamilyDto.setId(bookFamily.getId());
        bookFamilyDto.setName(bookFamily.getName());
        return bookFamilyDto;
    }

    public List<BookFamilyDto> entityToDto(List<BookFamily> bookFamilyList) {
        return bookFamilyList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public BookFamily dtoToEntity(BookFamilyDto bookFamilyDto) {
        BookFamily bookFamily = new BookFamily();
        bookFamily.setId(bookFamilyDto.getId());
        bookFamily.setName(bookFamilyDto.getName());
        return bookFamily;
    }

    public List<BookFamily> dtoToEntity(List<BookFamilyDto> bookFamilyDtoList) {
        return bookFamilyDtoList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}

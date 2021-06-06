package com.library.api.model.dto;

import com.library.api.model.BookFamily;
import lombok.Data;

import java.io.Serializable;

@Data
public class BookDto implements Serializable {
    private Long id;
    private String title;
    private BookFamily bookFamily;
}
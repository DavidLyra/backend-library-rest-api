package com.library.api.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookFamilyDto implements Serializable {
    private Long id;
    private String name;
}

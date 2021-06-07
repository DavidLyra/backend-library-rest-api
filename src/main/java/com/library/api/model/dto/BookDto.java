package com.library.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class BookDto implements Serializable {
    private Long id;
    @NotNull
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotNull
    private BookFamilyDto bookFamily;
}
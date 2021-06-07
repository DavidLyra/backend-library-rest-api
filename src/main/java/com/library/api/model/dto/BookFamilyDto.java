package com.library.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class BookFamilyDto implements Serializable {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
}

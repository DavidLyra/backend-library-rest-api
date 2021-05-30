package com.library.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Validated
@Entity
@Table(name="book_family")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookFamily implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @ApiModelProperty(notes = "Book Family ID", example = "1", required = true, hidden = true)
    private Long id;

    @Column(name="name")
    @ApiModelProperty(notes = "Book Family Name", example = "Technology", required = true)
    @NotBlank(message = "Name is mandatory")
    private String name;
}

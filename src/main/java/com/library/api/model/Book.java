package com.library.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Validated
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Book ID", example = "1", required = true, hidden = true)
    private Long id;

    @Column(name = "title")
    @NotBlank(message = "Title is mandatory")
    @ApiModelProperty(notes = "Title", example = "Book One", required = true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "book_family_id")
    @NotNull
    private BookFamily bookFamily;
}
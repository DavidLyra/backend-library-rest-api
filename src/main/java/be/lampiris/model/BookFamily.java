package be.lampiris.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Table(name="book_family")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookFamily implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name", unique=true)
    private String name;


    @ManyToOne
    @JoinColumn(name = "library_id", nullable = false)
    @JsonIgnore
    private Library library;


}

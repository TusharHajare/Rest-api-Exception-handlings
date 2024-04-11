package com.example.RestapiExceptionhandlings.models;

import lombok.*;

//import javax.persistence.Entity;
//import javax.persistence.Id;
import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "books")
public class Book
{
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookid;
    private String title;
    private String author;
    private String category;
}

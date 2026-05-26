package org.example.book.Models.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private Integer stock;

    private String coverUrl;
}
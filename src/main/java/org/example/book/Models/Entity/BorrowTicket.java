package org.example.book.Models.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.book.Models.Enum.BorrowStatus;

import java.time.LocalDate;

@Entity
@Table(name = "borrow_tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private Long bookId;

    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private BorrowStatus status;
}
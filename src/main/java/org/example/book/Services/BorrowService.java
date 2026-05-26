package org.example.book.Services;

import org.example.book.Exception.BookAlreadyReturnedException;
import org.example.book.Exception.ResourceNotFoundException;
import org.example.book.Models.Dto.BorrowCreateDTO;
import org.example.book.Models.Entity.Book;
import org.example.book.Models.Entity.Borrow;
import org.example.book.Models.Enum.BorrowStatus;
import org.example.book.Repositories.BookRepository;
import org.example.book.Repositories.BorrowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;

    private final BookRepository bookRepository;

    public BorrowService(
            BorrowRepository borrowRepository,
            BookRepository bookRepository
    ) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
    }

    public Borrow createBorrow(BorrowCreateDTO dto) {

        Borrow borrow = Borrow.builder()
                .username(dto.getUsername())
                .bookId(dto.getBookId())
                .status(BorrowStatus.BORROWING)
                .build();

        return borrowRepository.save(borrow);
    }

    public Borrow returnBook(Long ticketId) {

        Borrow ticket = borrowRepository.findById(ticketId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy phiếu mượn với id: " + ticketId
                        )
                );

        if (ticket.getStatus() == BorrowStatus.RETURNED) {

            throw new BookAlreadyReturnedException(
                    "Sách này đã được trả rồi"
            );
        }

        ticket.setReturnDate(LocalDate.now());
        ticket.setStatus(BorrowStatus.RETURNED);

        Book book = bookRepository.findById(ticket.getBookId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy sách"
                        )
                );

        book.setStock(book.getStock() + 1);

        bookRepository.save(book);

        return borrowRepository.save(ticket);
    }
}
package org.example.book.Services;
import org.example.book.Models.Dto.BorrowCreateDTO;
import org.example.book.Models.Entity.Borrow;
import org.example.book.Repositories.BorrowRepository;
import org.springframework.stereotype.Service;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;

    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public Borrow createBorrow(BorrowCreateDTO dto) {

        Borrow borrow = Borrow.builder()
                .username(dto.getUsername())
                .bookId(dto.getBookId())
                .build();

        return borrowRepository.save(borrow);
    }
}
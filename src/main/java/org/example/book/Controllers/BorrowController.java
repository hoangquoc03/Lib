package org.example.book.Controllers;

import jakarta.validation.Valid;
import org.example.book.Models.Dto.BorrowCreateDTO;
import org.example.book.Models.Entity.Borrow;
import org.example.book.Services.BorrowService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public Borrow createBorrow(
            @Valid @RequestBody BorrowCreateDTO dto
    ) {

        return borrowService.createBorrow(dto);
    }
    @PatchMapping("/{id}/return")
    public Borrow returnBook(
            @PathVariable Long id
    ) {

        return borrowService.returnBook(id);
    }
}
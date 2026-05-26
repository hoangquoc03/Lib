package org.example.book.Repositories;

import org.example.book.Models.Entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository
        extends JpaRepository<Borrow, Long> {
}
package org.example.book.Repositories;

import org.example.book.Models.Entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository
        extends JpaRepository<Reader, Long> {

    boolean existsByEmail(String email);
}
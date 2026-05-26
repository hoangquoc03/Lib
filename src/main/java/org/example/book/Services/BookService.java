package org.example.book.Services;


import org.example.book.Models.Dto.BookCreateDTO;
import org.example.book.Models.Entity.Book;
import org.example.book.Repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final Path rootFolder = Paths.get("uploads");

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        try {
            Files.createDirectories(rootFolder);
        } catch (IOException e) {
            throw new RuntimeException("Không thể khởi tạo thư mục lưu trữ file!", e);
        }
    }

    public Book createBook(BookCreateDTO dto) {
        String savedFileName = null;


        if (dto.getCoverImage() != null && !dto.getCoverImage().isEmpty()) {
            try {

                String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(dto.getCoverImage().getOriginalFilename()));


                savedFileName = UUID.randomUUID().toString() + "_" + originalFilename;


                Path targetPath = this.rootFolder.resolve(savedFileName);


                Files.copy(dto.getCoverImage().getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                throw new RuntimeException("Lỗi xảy ra trong quá trình lưu trữ file ảnh!", e);
            }
        }


        Book book = Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .stock(dto.getStock())
                .coverUrl(savedFileName)
                .build();
        return bookRepository.save(book);
    }
}

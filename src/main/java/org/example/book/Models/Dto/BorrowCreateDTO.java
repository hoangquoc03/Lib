package org.example.book.Models.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.example.book.Validation.ExistingBookId;

@Data
public class BorrowCreateDTO {

    @NotBlank(message = "Username không được để trống")
    private String username;

    @ExistingBookId
    private Long bookId;
}
package org.example.book.Controllers;

import jakarta.validation.Valid;
import org.example.book.Models.Dto.ReaderCreateDTO;
import org.example.book.Models.Entity.Reader;
import org.example.book.Services.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(
            ReaderService readerService
    ) {
        this.readerService = readerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reader createReader(
            @ModelAttribute @Valid ReaderCreateDTO dto
    ) {

        return readerService.createReader(dto);
    }
}
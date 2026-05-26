package org.example.book.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.book.Repositories.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class BookIdValidator
        implements ConstraintValidator<ExistingBookId, Long> {

    private final BookRepository bookRepository;

    public BookIdValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isValid(
            Long value,
            ConstraintValidatorContext context
    ) {

        // null để cho @NotNull xử lý riêng nếu cần
        if (value == null) {
            return true;
        }

        return bookRepository.existsById(value);
    }
}

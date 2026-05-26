package org.example.book.Models.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReaderCreateDTO {

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;

    @Pattern(
            regexp = "^(0|\\+84)[0-9]{9}$",
            message = "Số điện thoại không hợp lệ"
    )
    private String phoneNumber;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    // file upload
    private MultipartFile avatarFile;
}
package org.example.book.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.example.book.Exception.InvalidFileException;
import org.example.book.Exception.ResourceNotFoundException;
import org.example.book.Models.Dto.ReaderCreateDTO;
import org.example.book.Models.Entity.Reader;
import org.example.book.Repositories.ReaderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    private final Cloudinary cloudinary;

    public ReaderService(
            ReaderRepository readerRepository,
            Cloudinary cloudinary
    ) {
        this.readerRepository = readerRepository;
        this.cloudinary = cloudinary;
    }

    public Reader createReader(ReaderCreateDTO dto) {

        if (readerRepository.existsByEmail(dto.getEmail())) {

            throw new InvalidFileException(
                    "Email đã tồn tại"
            );
        }

        MultipartFile file = dto.getAvatarFile();

        if (file == null || file.isEmpty()) {

            throw new InvalidFileException(
                    "Vui lòng chọn ảnh"
            );
        }

        String contentType = file.getContentType();

        if (contentType == null ||
                (!contentType.equals("image/png")
                        && !contentType.equals("image/jpeg")
                        && !contentType.equals("image/jpg"))) {

            throw new InvalidFileException(
                    "Chỉ chấp nhận file png, jpg, jpeg"
            );
        }

        try {

            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap()
            );


            String imageUrl = uploadResult.get("url").toString();

            Reader reader = Reader.builder()
                    .email(dto.getEmail())
                    .fullName(dto.getFullName())
                    .phoneNumber(dto.getPhoneNumber())
                    .address(dto.getAddress())
                    .avatar(imageUrl)
                    .build();

            return readerRepository.save(reader);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Upload ảnh thất bại"
            );
        }
    }
}
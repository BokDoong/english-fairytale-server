package hanium.englishfairytale.tale.application;

import hanium.englishfairytale.exception.RuntimeIOException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.tale.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class FileManageService {

    private final FileStore fileStore;
    private final ImageRepository imageRepository;

    @Transactional
    public String uploadImage(Tale tale, MultipartFile image) {
        try {
            String storedName = ImageFileUtility.createObjectNameByUUID(image.getOriginalFilename());
            String imageUrl = fileStore.upload(storedName, image.getInputStream(), ImageFileUtility.createObjectMetadata(image));

            return saveImage(tale, image.getOriginalFilename(), storedName, imageUrl);
        } catch (IOException e) {
            throw new RuntimeIOException(e, ErrorCode.IMAGE_PROCESSING_IO);
        }
    }

    @Transactional
    public String saveImage(Tale tale, String originalName, String storedName, String imageUrl) {
        TaleImage taleImage = TaleImage.createTaleImage(tale, originalName, storedName, imageUrl);
        return imageRepository.save(taleImage);
    }
}

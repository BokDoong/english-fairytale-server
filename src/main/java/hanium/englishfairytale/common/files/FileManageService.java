package hanium.englishfairytale.common.files;

import hanium.englishfairytale.common.util.ImageFileUtility;
import hanium.englishfairytale.exception.RuntimeIOException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.tale.application.dto.TaleImageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class FileManageService {

    private final FileStore fileStore;

    @Transactional
    public TaleImageInfo uploadTaleImage(MultipartFile image) {
        try {
            String originalName = image.getOriginalFilename();
            String storedName = ImageFileUtility.createObjectNameByUUID(originalName);
            String imageUrl = fileStore.upload(storedName, image.getInputStream(), ImageFileUtility.createObjectMetadata(image));

            return new TaleImageInfo(originalName, storedName, imageUrl);
        } catch (IOException e) {
            throw new RuntimeIOException(e, ErrorCode.IMAGE_PROCESSING_IO);
        }
    }
}

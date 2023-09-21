package hanium.englishfairytale.tale.domain;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileStore {

    String upload(String storedName, InputStream inputStream, ObjectMetadata objectMetadata);

    void delete(String storedName);
}

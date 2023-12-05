package hanium.englishfairytale.common.files;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class FileStore {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(String storedName, InputStream inputStream, ObjectMetadata objectMetadata) {
        amazonS3Client.putObject(bucket, storedName, inputStream, objectMetadata);
        return amazonS3Client.getUrl(bucket, storedName).toString();
    }

    public void delete(String storedName) {
        amazonS3Client.deleteObject(bucket, storedName);
    }
}

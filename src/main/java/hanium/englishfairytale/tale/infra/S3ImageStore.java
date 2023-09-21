package hanium.englishfairytale.tale.infra;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import hanium.englishfairytale.tale.domain.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class S3ImageStore implements FileStore {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String upload(String storedName, InputStream inputStream, ObjectMetadata objectMetadata) {
        amazonS3Client.putObject(bucket, storedName, inputStream, objectMetadata);
        return amazonS3Client.getUrl(bucket, storedName).toString();
    }

    @Override
    public void delete(String storedName) {
        amazonS3Client.deleteObject(bucket, storedName);
    }
}

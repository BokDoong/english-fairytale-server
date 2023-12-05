package hanium.englishfairytale.common.util;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;
import reactor.netty.internal.util.MapUtils;

import java.util.UUID;

@UtilityClass
public class ImageFileUtility {

    // S3에 저장할 이름으로 변경
    public String createObjectNameByUUID(String originalFileName) {
        return UUID.randomUUID() + originalFileName;
    }

    // MetaData 설정
    public ObjectMetadata createObjectMetadata(MultipartFile multipartFile) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        return metadata;
    }

}

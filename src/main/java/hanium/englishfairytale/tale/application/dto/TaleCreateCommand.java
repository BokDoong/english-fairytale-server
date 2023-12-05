package hanium.englishfairytale.tale.application.dto;

import hanium.englishfairytale.tale.domain.ImageStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Getter
public class TaleCreateCommand {
    private long memberId;
    private String model;
    private List<String> keywords;
    private MultipartFile image;
    private ImageStatus imageStatus;
}

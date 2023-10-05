package hanium.englishfairytale.tale.application.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
public class TaleUpdateCommand {
    private Long taleId;
    private MultipartFile image;
}

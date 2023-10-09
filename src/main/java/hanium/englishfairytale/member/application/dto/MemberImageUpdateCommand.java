package hanium.englishfairytale.member.application.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
public class MemberImageUpdateCommand {
    private Long memberId;
    private MultipartFile image;
}

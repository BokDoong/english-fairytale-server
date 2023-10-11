package hanium.englishfairytale.member.application.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
public class MemberRegisterCommand {
    private String name;
    private String phoneNumber;
    private String nickname;
    private String email;
    private String password;
    private MultipartFile image;

}

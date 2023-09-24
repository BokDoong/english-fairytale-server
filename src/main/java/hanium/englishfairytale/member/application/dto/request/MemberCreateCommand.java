package hanium.englishfairytale.member.application.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberCreateCommand {
    private String name;
    private String phoneNumber;
    private String nickname;
    private String email;
    private String password;
}

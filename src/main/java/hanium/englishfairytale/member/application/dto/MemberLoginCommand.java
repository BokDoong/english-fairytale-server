package hanium.englishfairytale.member.application.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberLoginCommand {
    String email;
    String password;
}

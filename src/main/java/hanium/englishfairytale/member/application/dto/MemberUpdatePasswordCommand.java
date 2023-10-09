package hanium.englishfairytale.member.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdatePasswordCommand {
    Long memberId;
    String password;
}

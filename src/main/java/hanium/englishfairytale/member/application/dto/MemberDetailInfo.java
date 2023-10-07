package hanium.englishfairytale.member.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDetailInfo {
    String email;
    String phoneNumber;
    String imageUrl;
}

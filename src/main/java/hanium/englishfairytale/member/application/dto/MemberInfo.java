package hanium.englishfairytale.member.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfo {
    String nickname;
    String imageUrl;
    int taleCounts;
}

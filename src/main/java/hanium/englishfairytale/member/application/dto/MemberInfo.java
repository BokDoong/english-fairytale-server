package hanium.englishfairytale.member.application.dto;

import hanium.englishfairytale.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberInfo {
    String nickname;
    String imageUrl;
    Long taleCounts;

    public MemberInfo(Member member, Long taleCounts) {
        this.nickname = member.getNickname();
        this.imageUrl = member.getImage() == null ? null : member.getImage().getUrl();
        this.taleCounts = taleCounts;
    }
}

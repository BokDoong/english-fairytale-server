package hanium.englishfairytale.member.application.dto;

import hanium.englishfairytale.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberInfo {
    private String nickname;
    private String imageUrl;
    private Long taleCounts;

    public MemberInfo(Member member, Long taleCounts) {
        this.nickname = member.getNickname();
        this.imageUrl = member.getImage() == null ? null : member.getImage().getUrl();
        this.taleCounts = taleCounts;
    }
}

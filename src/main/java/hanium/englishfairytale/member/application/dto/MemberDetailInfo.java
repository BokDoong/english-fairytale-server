package hanium.englishfairytale.member.application.dto;

import hanium.englishfairytale.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberDetailInfo {
    private String nickname;
    private String email;
    private String phoneNumber;
    private String imageUrl;

    public MemberDetailInfo(Member member) {
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.phoneNumber = member.getPhoneNumber();
        this.imageUrl = member.getImage() == null ? null : member.getImage().getUrl();
    }
}

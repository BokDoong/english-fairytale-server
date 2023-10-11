package hanium.englishfairytale.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="memberImage_id")
    private MemberImage memberImage;

    void putMemberImage(MemberImage newMemberImage) {
        memberImage = newMemberImage;
    }
    public String getUrl() {
        return memberImage.getImageUrl();
    }
}

package hanium.englishfairytale.member.domain;

import lombok.Getter;

import javax.persistence.*;

@Embeddable
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

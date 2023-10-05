package hanium.englishfairytale.member.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NotNull
    @Column(name = "original_name")
    private String originalName;
    @NotNull
    @Column(name = "stored_name")
    private String storedName;
    @NotNull
    @Column(name = "image_url")
    private String imageUrl;

    public static MemberImage createMemberImage(Member member, String originalName, String storedName, String imageUrl) {
        MemberImage memberImage = setMemberImage(originalName, storedName, imageUrl);
        member.putImage(memberImage);
        return memberImage;
    }

    private static MemberImage setMemberImage(String originalName, String storedName, String imageUrl) {
        return MemberImage.builder()
                .originalName(originalName)
                .storedName(storedName)
                .imageUrl(imageUrl)
                .build();
    }
}

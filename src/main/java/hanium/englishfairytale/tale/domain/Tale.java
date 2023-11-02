package hanium.englishfairytale.tale.domain;

import hanium.englishfairytale.exception.BusinessException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.post.application.dto.PostedTalesInfo;
import hanium.englishfairytale.post.domain.Post;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Tale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title", length = 100)
    private String title;
    @Column(name = "content", length = 5000)
    private String engTale;
    @Column(name = "kor",length = 3000)
    private String korTale;
    @Embedded
    private Image image;
    @Embedded
    private Post post;
    @Column(name = "created_date")
    private LocalDateTime createdTime;

    @OneToMany(mappedBy = "tale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaleKeyword> taleKeywords = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Tale(String title, String engTale, String korTale, Member member, ImageStatus imageStatus) {
        this.title = title;
        this.engTale = engTale;
        this.korTale = korTale;
        this.image = new Image(imageStatus);
        this.createdTime = LocalDateTime.now();
        this.member = member;
        this.post = new Post();
        member.addTale(this);
    }

    public static List<PostedTalesInfo> sortPostedTalesByLikes(List<PostedTalesInfo> postedTalesInfo) {
        postedTalesInfo.sort(Comparator.comparingInt(PostedTalesInfo::getLikeCounts));
        Collections.reverse(postedTalesInfo);
        return postedTalesInfo;
    }

    public static List<Long> toTaleIds(List<PostedTalesInfo> postedTalesInfos) {
        return postedTalesInfos.stream()
                .map(PostedTalesInfo::getTaleId)
                .collect(Collectors.toList());
    }

    public boolean updateLike(Long memberId) {
        return post.updateLikeStatus(memberId, this);
    }

    public void verifyPostAlreadyExisted() {
        post.verifyAlreadyPosted();
    }

    public String getMemberName() {
        return member.getName();
    }

    public void putImage(TaleImage taleImage) {
        image.putTaleImage(taleImage);
    }

    public void addTaleKeyword(TaleKeyword newTaleKeyword) {
        taleKeywords.add(newTaleKeyword);
    }

    public void updateTaleImage(TaleImage taleImage) {
        image.putTaleImage(taleImage);
        image.updateImageStatus();
    }

    public void putBasicImage(String imageStatus) {
        image = new Image(ImageStatus.of(imageStatus));
    }

    public void checkImageEmpty() {
        if (image.getTaleImage() == null) {
            throw new BusinessException(ErrorCode.TALE_IMAGE_NON_EXISTED);
        }
    }

    public Long getImageId() {
        return image.getTaleImage().getId();
    }

    public String getImageStatus() {
        return image.getImageStatus().getStatus();
    }

    public void posting() {
        post.updatePostStatus();
        post.updatePostDate();
    }
    public void deletePosting() {
        post.updatePostStatus();
    }

    public void verifyPostNotExisted() {
        post.verifyNotExited();
    }

    public int countLikes() {
        return post.getLikeCounts();
    }
}

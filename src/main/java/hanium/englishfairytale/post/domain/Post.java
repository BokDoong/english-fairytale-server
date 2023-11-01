package hanium.englishfairytale.post.domain;

import hanium.englishfairytale.exception.BusinessException;
import hanium.englishfairytale.exception.code.ErrorCode;
import hanium.englishfairytale.tale.domain.Tale;
import lombok.Getter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
public class Post {
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;
    @Column(name = "posted_date")
    private LocalDateTime postedTime;

    @OneToMany(mappedBy = "tale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

    public Post() {
        this.postStatus = PostStatus.NOT_POSTED;
        this.postedTime = LocalDateTime.now();
    }

    public void verifyAlreadyPosted() {
        if (postStatus == PostStatus.POSTED) {
            throw new BusinessException(ErrorCode.EXISTED_POST);
        }
    }

    public void updatePostStatus() {
        if (postStatus == PostStatus.NOT_POSTED) {
            postStatus = PostStatus.POSTED;
        } else {
            postStatus = PostStatus.NOT_POSTED;
        }
    }

    public void verifyNotExited() {
        if (postStatus == PostStatus.NOT_POSTED) {
            throw new BusinessException(ErrorCode.POST_NOT_FOUND);
        }
    }

    public boolean updateLikeStatus(Long memberId, Tale tale) {
        boolean liked = verifyAlreadyLiked(memberId);
        if (liked) {
            likes.removeIf(like -> (like.getMemberId().equals(memberId)));
            return false;
        } else {
            Likes like = new Likes(memberId);
            like.setPost(tale);
            likes.add(like);
            return true;
        }
    }

    public boolean verifyAlreadyLiked(Long memberId) {
        return likes.stream()
                .anyMatch(like -> like.getMemberId().equals(memberId));
    }

    public int getLikeCounts() {
        return likes.size();
    }

    public void updatePostDate() {
        postedTime = LocalDateTime.now();
    }
}

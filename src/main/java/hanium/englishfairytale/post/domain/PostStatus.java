package hanium.englishfairytale.post.domain;

import lombok.Getter;

@Getter
public enum PostStatus {
    POSTED("포스팅 게시물"),
    NOT_POSTED("포스팅 되지 않은 게시물"),
    ;

    private final String status;

    PostStatus(String status) {
        this.status = status;
    }
}

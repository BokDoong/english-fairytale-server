package hanium.englishfairytale.post.domain;

import hanium.englishfairytale.tale.domain.Tale;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Optional<Tale> findPostedTale(Long taleId);

    List<Tale> findPostedTaleList(int offset);

    // 게시글 제목으로 조회
    List<Tale> findPostedTaleListByTitle(int offset, int limit, String title);

    List<Tale> findPostedTalesSortedByDate(int offset, int limit);

    List<Tale> findPostedTaleListByMemberId(Long memberId, int offset, int limit);

    List<Tale> findLikedPostedTaleListByMemberId(Long memberId, int offset, int limit);
}

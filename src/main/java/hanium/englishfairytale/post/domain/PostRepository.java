package hanium.englishfairytale.post.domain;

import hanium.englishfairytale.tale.domain.Tale;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Optional<Tale> findPostedTaleWithLikes(Long taleId);

    List<Tale> findPostedTaleListWithLikes(int offset);

    List<Tale> findPostedTalesSortedByDate(int offset, int limit);

    List<Tale> findPostedTaleListByMemberId(Long memberId, int offset, int limit);

    List<Tale> findLikedPostedTalesByMemberId(Long memberId, int offset, int limit);
}

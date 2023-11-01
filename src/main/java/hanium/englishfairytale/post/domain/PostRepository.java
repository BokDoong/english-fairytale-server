package hanium.englishfairytale.post.domain;

import hanium.englishfairytale.tale.domain.Tale;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Optional<Tale> findTaleWithLikes(Long taleId);

    List<Tale> findPostedTalesSortedByDate(int offset, int limit);
}

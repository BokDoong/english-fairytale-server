package hanium.englishfairytale.post.infra;

import hanium.englishfairytale.post.application.dto.PostedTalesInfo;
import hanium.englishfairytale.post.domain.PostStatus;
import hanium.englishfairytale.post.infra.dto.PostLikesQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostQueryDao {

    private final EntityManager em;

    // 게시글 조회
    public List<PostedTalesInfo> findPostedTales(int offset, int limit) {
        return em.createQuery(
                "select new hanium.englishfairytale.post.application.dto.PostedTalesInfo" +
                        "(t.id, t.title, m.nickname, ti.imageUrl)" +
                        " from Tale t" +
                        " join t.member m" +
                        " left join t.image.taleImage ti" +
                        " where t.post.postStatus = :postStatus", PostedTalesInfo.class)
                .setParameter("postStatus", PostStatus.POSTED)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    // 좋아요 조회
    public Map<Long, List<PostLikesQueryDto>> findPostLikesMap(List<Long> postedTaleIds) {
        List<PostLikesQueryDto> postLikes = em.createQuery(
                "select new hanium.englishfairytale.post.infra.dto.PostLikesQueryDto" +
                        "(l.tale.id, l.memberId)" +
                        " from Likes l" +
                        " where l.tale.id in :postedTaleId", PostLikesQueryDto.class)
                .setParameter("postedTaleId", postedTaleIds)
                .getResultList();

        return postLikes.stream()
                .collect(Collectors.groupingBy(PostLikesQueryDto::getTaleId));
    }
}

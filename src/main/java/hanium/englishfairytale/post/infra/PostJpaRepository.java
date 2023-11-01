package hanium.englishfairytale.post.infra;

import hanium.englishfairytale.post.domain.PostRepository;
import hanium.englishfairytale.post.domain.PostStatus;
import hanium.englishfairytale.tale.domain.Tale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostJpaRepository implements PostRepository {

    private final EntityManager em;

    // 동화+좋아요 조회
    public Optional<Tale> findTaleWithLikes(Long taleId) {
        List<Tale> tales =  em.createQuery(
                        "select distinct t from Tale t" +
                                " join fetch t.member m" +
                                " left join t.post.likes l" +
                                " where t.id = :taleId", Tale.class
                )
                .setParameter("taleId", taleId)
                .getResultList();
        return tales.stream().findAny();
    }

    // 게시글 날짜순 조회
    @Override
    public List<Tale> findPostedTalesSortedByDate(int offset, int limit) {
        return em.createQuery(
                        "select t from Tale t" +
                                " join fetch t.member m" +
                                " left join fetch t.image.taleImage ti" +
                                " where t.post.postStatus = :postStatus" +
                                " order by t.post.postedTime desc", Tale.class
                )
                .setParameter("postStatus", PostStatus.POSTED)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

}

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

    // TODO: 키워드 중복 조회 -> 페이징 오류 개선
    // 포스팅 동화 단건조회
    @Override
    public Optional<Tale> findPostedTale(Long taleId) {
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

    // 포스팅 동화 리스트 조회
    @Override
    public List<Tale> findPostedTaleList(int offset) {
        return em.createQuery(
                "select t from Tale t" +
                        " join fetch t.member m" +
                        " left join fetch t.image.taleImage ti" +
                        " where t.post.postStatus = :postStatus", Tale.class
        )
                .setParameter("postStatus", PostStatus.POSTED)
                .setFirstResult(offset)
                .setMaxResults(20)
                .getResultList();
    }

    // 게시글 제목으로 조회
    @Override
    public List<Tale> findPostedTaleListByTitle(int offset, int limit, String searchTitle) {
        return em.createQuery(
                "select t from Tale t" +
                        " join fetch t.member m" +
                        " left join fetch t.image.taleImage ti" +
                        " where t.post.postStatus = :postStatus" +
                        " and t.title like concat('%', :searchTitle, '%')" +
                        " order by t.post.postedTime desc", Tale.class
        )
                .setParameter("postStatus", PostStatus.POSTED)
                .setParameter("searchTitle", searchTitle)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    // 포스팅 동화 날짜순 조회
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

    // 게시글 전체 조회
    @Override
    public List<Tale> findPostedTaleListByMemberId(Long memberId, int offset, int limit) {
        return em.createQuery(
                "select t from Tale t" +
                        " join fetch t.member m" +
                        " left join fetch t.image.taleImage ti" +
                        " where t.member.id = :memberId and t.post.postStatus = :postStatus", Tale.class
        )
                .setParameter("postStatus", PostStatus.POSTED)
                .setParameter("memberId", memberId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    // 좋아요 게시글 조회
    @Override
    public List<Tale> findLikedPostedTaleListByMemberId(Long memberId, int offset, int limit) {
        return em.createQuery(
                "select t from Tale t" +
                        " join fetch t.member m" +
                        " left join fetch t.image.taleImage ti" +
                        " join t.post.likes l" +
                        " where l.memberId = :memberId", Tale.class
        )
                .setParameter("memberId", memberId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}

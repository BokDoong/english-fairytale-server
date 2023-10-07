package hanium.englishfairytale.tale.infra;

import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.Tale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaleQueryDao {

    private final EntityManager em;

    // 동화 목록조회(페이징)
    public List<Tale> findTalesByMemberId(Long memberId, int offset, int limit) {
        return em.createQuery(
                        "select t from Tale t" +
                                " join fetch t.member m" +
                                " left join fetch t.image.taleImage ti" +
                                " where m.id = :memberId" +
                                " order by t.createdTime desc", Tale.class
                )
                .setParameter("memberId", memberId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    // 동화 단건조회
    public Optional<Tale> findTaleByTaleId(Long taleId) {
        List<Tale> tales =  em.createQuery(
                        "select t from Tale t" +
                                " join fetch t.member m" +
                                " left join fetch t.image.taleImage ti" +
                                " where t.id = :taleId", Tale.class
                )
                .setParameter("taleId", taleId)
                .getResultList();
        return tales.stream().findAny();
    }

    // 키워드 조회(TaleKeywordId)
    public List<Keyword> findKeywordByTaleId(Long taleId) {
        return em.createQuery(
                        "select k from Keyword k" +
                                " join k.taleKeywords tk" +
                                " where tk.tale.id = :taleId", Keyword.class
                )
                .setParameter("taleId", taleId)
                .getResultList();
    }

    // 동화개수 조회
    public int countTales(Long memberId) {
        return em.createQuery(
                "select count(nullif(t, 0)) from Tale t" +
                        " where t.member.id = :memberId"
        )
                .setParameter("memberId", memberId)
                .getFirstResult();
    }
}

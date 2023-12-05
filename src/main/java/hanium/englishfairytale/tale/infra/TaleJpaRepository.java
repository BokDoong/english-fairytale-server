package hanium.englishfairytale.tale.infra;

import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.Tale;
import hanium.englishfairytale.tale.domain.TaleKeyword;
import hanium.englishfairytale.tale.domain.TaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaleJpaRepository implements TaleRepository {

    private final EntityManager em;

    @Override
    public void save(TaleKeyword taleKeyword) {
        em.persist(taleKeyword);
    }

    @Override
    public Optional<Keyword> findByWord(String word) {
        List<Keyword> keywords = em.createQuery("select k from Keyword k where k.word in :word", Keyword.class)
                .setParameter("word", word)
                .getResultList();

        return keywords.stream().findAny();
    }

    // 동화 목록조회(페이징)
    @Override
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
    @Override
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
    @Override
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
    @Override
    public Long countTales(Long memberId) {
        return (Long) em.createQuery(
                        "select count(nullif(t, 0)) from Tale t" +
                                " where t.member.id = :memberId"
                )
                .setParameter("memberId", memberId)
                .getSingleResult();
    }

    @Override
    public void deleteByTaleId(Long taleId) {
        Tale tale = em.find(Tale.class, taleId);
        em.remove(tale);
    }
}

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
        List<Keyword> keywords = em.createQuery("select k from Keyword k where k.word = :word", Keyword.class)
                .setParameter("word", word)
                .getResultList();

        return keywords.stream().findAny();
    }

    @Override
    public List<Tale> findTalesList(Long userId, int offset, int limit) {
        return em.createQuery(
                "select t from Tale t" +
                        " join fetch t.member m" +
                        " join fetch t.image i" +
                        " join fetch i.taleImage ti" +
                        " where m.id = :userId" +
                        " order by t.createdTime DESC", Tale.class
        )
                .setParameter("userId", userId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public Optional<Tale> findTaleDetailInfo(Long taleId) {
        List<Tale> tales = em.createQuery(
                "select distinct t from Tale t" +
                        " join fetch t.member m" +
                        " join fetch t.image i" +
                        " join fetch i.taleImage ti" +
                        " join fetch t.taleKeywords tk" +
                        " join fetch tk.keyword k" +
                        " where t.id = :taleId", Tale.class
        )
                .setParameter("taleId", taleId)
                .getResultList();

        return tales.stream().findAny();
    }


}

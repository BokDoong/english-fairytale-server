package hanium.englishfairytale.tale.infra;

import hanium.englishfairytale.tale.domain.Keyword;
import hanium.englishfairytale.tale.domain.TaleKeyword;
import hanium.englishfairytale.tale.domain.TaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.security.Key;
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
}

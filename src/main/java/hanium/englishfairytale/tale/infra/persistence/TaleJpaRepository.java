package hanium.englishfairytale.tale.infra.persistence;

import hanium.englishfairytale.tale.domain.TaleKeyword;
import hanium.englishfairytale.tale.domain.TaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class TaleJpaRepository implements TaleRepository {

    private final EntityManager em;

    @Override
    public void save(TaleKeyword taleKeyword) {
        em.persist(taleKeyword);
    }
}

package hanium.englishfairytale.tale.infra;

import hanium.englishfairytale.tale.domain.ImageRepository;
import hanium.englishfairytale.tale.domain.TaleImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ImageJpaRepository implements ImageRepository {

    private final EntityManager em;

    @Override
    public String save(TaleImage taleImage) {
        em.persist(taleImage);
        return taleImage.getImageUrl();
    }
}

package hanium.englishfairytale.announcement.infra;

import hanium.englishfairytale.announcement.domain.Announcement;
import hanium.englishfairytale.announcement.domain.AnnouncementRepository;
import hanium.englishfairytale.announcement.domain.AnnouncementType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnnouncementJpaRepository implements AnnouncementRepository {

    private final EntityManager em;

    @Override
    public List<Announcement> findByType(AnnouncementType announcementType) {
        return em.createQuery("select a from Announcement a" +
                " where a.announcementType = :announcementType", Announcement.class)
                .setParameter("announcementType", announcementType)
                .getResultList();
    }
}

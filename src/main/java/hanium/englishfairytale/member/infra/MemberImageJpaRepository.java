package hanium.englishfairytale.member.infra;

import hanium.englishfairytale.member.domain.ImageRepository;
import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.member.domain.MemberImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberImageJpaRepository implements ImageRepository {

    private final EntityManager em;

    @Override
    public void delete(Long memberImageId) {
        MemberImage memberImage = em.find(MemberImage.class, memberImageId);
        em.remove(memberImage);
    }
}

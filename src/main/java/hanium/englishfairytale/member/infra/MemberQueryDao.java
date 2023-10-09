package hanium.englishfairytale.member.infra;

import hanium.englishfairytale.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberQueryDao {

    private final EntityManager em;

    // 회원+사진 조회
    public Optional<Member> findMemberAndImage(Long memberId) {
        List<Member> members = em.createQuery(
                "select m from Member m" +
                        " left join fetch m.image.memberImage mi" +
                        " where m.id = :memberId", Member.class
        )
                .setParameter("memberId" ,memberId)
                .getResultList();
        return members.stream().findAny();
    }

}

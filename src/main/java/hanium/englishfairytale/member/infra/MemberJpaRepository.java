package hanium.englishfairytale.member.infra;

import hanium.englishfairytale.member.domain.Member;
import hanium.englishfairytale.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.Cacheable;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository implements MemberRepository {

    private final EntityManager em;

    @Override
    public Optional<Member> findMemberById(Long memberId) {
        return Optional.ofNullable(em.find(Member.class, memberId));
    }

    @Override
    public Optional<Member> findMemberByEmail(String email) {
        List<Member> members = em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
        return members.stream().findAny();
    }

    @Override
    public Optional<Member> findMemberByPhoneNumber(String phoneNumber) {
        List<Member> members = em.createQuery("select m from Member m where m.phoneNumber = :phoneNumber", Member.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList();
        return members.stream().findAny();
    }

    @Override
    public Optional<Member> findMemberByNickname(String nickName) {
        List<Member> members = em.createQuery("select m from Member m where m.nickname = :nickName", Member.class)
                .setParameter("nickName", nickName)
                .getResultList();
        return members.stream().findAny();
    }

    @Override
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }
}
